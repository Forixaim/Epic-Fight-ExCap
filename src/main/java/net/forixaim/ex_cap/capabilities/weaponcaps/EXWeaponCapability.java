package net.forixaim.ex_cap.capabilities.weaponcaps;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.network.server.SPSetRemotePlayerSkill;
import yesman.epicfight.network.server.SPSetSkillContainerValue;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.registry.entries.*;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A custom implementation of the original WeaponCapability class with a focus on modularity with a conditional passive skill provider.
 */
public class EXWeaponCapability extends WeaponCapability
{
	protected final Map<Style, Map<ClashType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> clashes;
	protected final Map<Style, Skill> weaponPassiveSkill;
	protected final Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> chantAnimations;
	protected final Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> castAnimations;
	protected final Map<Style, Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> battleModeAnimations;
	protected final Map<Style, AnimationManager.AnimationAccessor<?>> battleTransitionAnimations;
	protected final Map<Style, Map<GuardSkill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>> guardAnimations;
	protected final Map<Style, Predicate<LivingEntityPatch<?>>> shouldRenderSheath;
	protected final Map<Item, Item> sheath;
	protected final Map<Style, List<AnimationManager.AnimationAccessor<? extends AttackAnimation>>> mountAttackAnimations;
	protected final Map<Style, AnimationManager.AnimationAccessor<? extends AttackAnimation>> punishmentAnimation;

	@Override
	public LivingMotion getLivingMotion(LivingEntityPatch<?> entityPatch, InteractionHand hand) {
		if (entityPatch.getOriginal().isUsingItem() && entityPatch.getOriginal().getUseItem().getUseAnimation() == UseAnim.BOW) {
			return LivingMotions.AIM;
		}
		return super.getLivingMotion(entityPatch, hand);
	}

    @Override
    public boolean checkOffhandValid(LivingEntityPatch<?> entitypatch) {
        try
        {
            return super.checkOffhandValid(entitypatch) || this.weaponCombinationPredicator.apply(entitypatch);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> getMountAttackEX(Style style)
	{
		if (mountAttackAnimations.get(style) == null) {
			return getMountAttackMotion();
		}
		return mountAttackAnimations.get(style);
	}

	protected EXWeaponCapability(WeaponCapability.Builder builder)
	{
		super(builder);
		Builder efbsBuilder = (Builder) builder;
		this.punishmentAnimation = efbsBuilder.punishmentAnimation;
		this.shouldRenderSheath = efbsBuilder.shouldRenderSheath;
		this.battleTransitionAnimations = efbsBuilder.battleTransitionAnimations;
		this.battleModeAnimations = efbsBuilder.battleModeAnimations;
		this.castAnimations = efbsBuilder.castAnimations;
		this.chantAnimations = efbsBuilder.chantAnimations;
		this.guardAnimations = efbsBuilder.guardAnimations;
		this.mountAttackAnimations = efbsBuilder.mountAttackAnimation;
		this.weaponPassiveSkill = efbsBuilder.weaponPassiveSkill;
		this.sheath = efbsBuilder.sheath;
		this.clashes = efbsBuilder.clashes;
	}

	public AnimationManager.AnimationAccessor<? extends AttackAnimation> getRevelationAnimation(PlayerPatch<?> playerPatch)
	{
		return punishmentAnimation.get(this.getStyle(playerPatch));
	}

	public Item getSheath(Item target)
	{
		if (!sheath.containsKey(target))
			return EpicFightItems.UCHIGATANA_SHEATH.get();
		return sheath.get(target);
	}

	public Predicate<LivingEntityPatch<?>> getSheathRenderer(Style style)
	{
		if (sheath == null)
		{
			return livingEntityPatch -> false;
		}
		if (!shouldRenderSheath.containsKey(style))
		{
			return livingEntityPatch -> false;
		}
		return shouldRenderSheath.get(style);
	}

	public Map<Style, AnimationManager.AnimationAccessor<?>> getBattleTransitionAnimations()
	{
		return battleTransitionAnimations;
	}

	public Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>  getCastAnimations()
	{
		return castAnimations;
	}

	public Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>  getChantAnimations()
	{
		return chantAnimations;
	}


	@Override
	public void changeWeaponInnateSkill(ServerPlayerPatch playerpatch, ItemStack itemstack)
	{
		Skill weaponInnateSkill = this.getInnateSkill(playerpatch, itemstack);
        EpicFightNetworkManager.PayloadBundleBuilder toLocal = EpicFightNetworkManager.PayloadBundleBuilder.create();
		EpicFightNetworkManager.PayloadBundleBuilder toRemote = EpicFightNetworkManager.PayloadBundleBuilder.create();
		SkillContainer weaponInnateSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_INNATE);
		if (weaponInnateSkill != null) {
			if (weaponInnateSkillContainer.getSkill() != weaponInnateSkill) {
				weaponInnateSkillContainer.setSkill(weaponInnateSkill);
			}
            toLocal.and(new SPChangeSkill(SkillSlots.WEAPON_INNATE, playerpatch.getOriginal().getId(), weaponInnateSkill.holder()));
        } else {
            toLocal.and(SPSetSkillContainerValue.enable(SkillSlots.WEAPON_INNATE, true, playerpatch.getOriginal().getId()));
		}

		weaponInnateSkillContainer.setDisabled(weaponInnateSkill == null);
        toRemote.and(new SPSetRemotePlayerSkill(SkillSlots.WEAPON_INNATE, playerpatch.getOriginal().getId(), Skill.holderOrNull(weaponInnateSkill)));
		Skill skill = weaponPassiveSkill.get(this.getStyle(playerpatch));
		SkillContainer passiveSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE);
		if (skill != null) {
			if (passiveSkillContainer.getSkill() != skill) {
				passiveSkillContainer.setSkill(skill);
                toLocal.and(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, playerpatch.getOriginal().getId(), passiveSkill.holder()));
                toRemote.and(new SPSetRemotePlayerSkill(SkillSlots.WEAPON_PASSIVE, playerpatch.getOriginal().getId(), passiveSkill.holder()));
			}
		} else {
			passiveSkillContainer.setSkill(null);
			toLocal.and(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, playerpatch.getOriginal().getId(), null));
            toRemote.and(new SPSetRemotePlayerSkill(SkillSlots.WEAPON_PASSIVE, playerpatch.getOriginal().getId(), null));
		}

		toLocal.send((first, others) -> EpicFightNetworkManager.sendToPlayer(first, (ServerPlayer)playerpatch.getOriginal(), others));
		toRemote.send((first, others) -> EpicFightNetworkManager.sendToAllPlayerTrackingThisEntity(first, playerpatch.getOriginal(), others));
	}

	public AnimationManager.AnimationAccessor<? extends StaticAnimation> getClashAnimation( ClashType clashType, LivingEntityPatch<?> livingEntityPatch)
	{
		List<AnimationManager.AnimationAccessor<? extends StaticAnimation>> clashAnimations = clashes.get(this.getStyle(livingEntityPatch)).get(clashType);

		return clashAnimations.isEmpty() ? null : clashAnimations.get(0);
	}

	@Override
	public AnimationManager.AnimationAccessor<? extends StaticAnimation> getGuardMotion(GuardSkill skill, GuardSkill.BlockType blockType, PlayerPatch<?> playerpatch)
	{
		Style style = this.getStyle(playerpatch);
		if (guardAnimations.containsKey(style) && guardAnimations.get(style).containsKey(skill) && guardAnimations.get(style).get(skill).containsKey(blockType) && playerpatch.getSkill(skill) != null)
		{
			List<AnimationManager.AnimationAccessor<? extends StaticAnimation>> animations = guardAnimations.get(style).get(skill).get(blockType);
			SkillDataManager dataManager = playerpatch.getSkill(skill).getDataManager();
			if (animations != null && !animations.isEmpty())
			{
				if (dataManager.hasData(EpicFightSkillDataKeys.PARRY_MOTION_COUNTER) && blockType == GuardSkill.BlockType.ADVANCED_GUARD)
				{
					int motionCounter = dataManager.getDataValue(EpicFightSkillDataKeys.PARRY_MOTION_COUNTER);
					dataManager.setDataF(EpicFightSkillDataKeys.PARRY_MOTION_COUNTER, (v) -> v + 1);
					motionCounter %= animations.size();
					return animations.get(motionCounter);
				}
				return animations.get(playerpatch.getOriginal().getRandom().nextInt(animations.size()));
			}
		}
		return super.getGuardMotion(skill, blockType, playerpatch);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder copy(Builder copyFrom)
	{
        return (Builder) builder()
                .collider(copyFrom.colliderCopy)
                .category(copyFrom.copyCategory)
                .constructor(EXWeaponCapability::new)
                .hitParticle(copyFrom.hitParticleCopy)
                .canBePlacedOffhand(copyFrom.offHandPlacementCopy)
                .swingSound(copyFrom.swingSoundCopy)
                .hitSound(copyFrom.hitSoundCopy);
	}

    private Object findComponentArgument(Component component, String key) {
        ComponentContents var4 = component.getContents();
        if (var4 instanceof TranslatableContents contents) {
            if (contents.getKey().equals(key)) {
                return component;
            }

            for (Object arg : contents.getArgs()) {
                if (arg instanceof Component argComponent) {
                    Object ret = this.findComponentArgument(argComponent, key);
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }

        for(Component siblingComponent : component.getSiblings()) {
            Object ret = this.findComponentArgument(siblingComponent, key);
            if (ret != null) {
                return ret;
            }
        }

        return null;
    }

    public enum ClashType
	{
		BLADE,
		BLUNT
	}

	/**
	 * The modified builder that allows for easier weapon style creation and conditional passive skill providers.
	 */
	public static class Builder extends WeaponCapability.Builder
	{
		protected final Map<Style, Map<ClashType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> clashes;
		//The Object parameter is to be cast into CastType class
		protected final Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>  castAnimations;
		protected final Map<Style, Map<Object, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>  chantAnimations;
		protected Map<Style, Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> battleModeAnimations;
		protected final Map<Style, AnimationManager.AnimationAccessor<? >> battleTransitionAnimations;
		protected final Map<Style, Skill> weaponPassiveSkill;
		protected final Map<Style, Predicate<LivingEntityPatch<?>>> shouldRenderSheath;
		protected final Map<Item, Item> sheath;
		protected final Map<Style, AnimationManager.AnimationAccessor<? extends AttackAnimation>> punishmentAnimation;
		protected final Map<Style, List<AnimationManager.AnimationAccessor<? extends AttackAnimation>>> mountAttackAnimation;

		//Fields that are primarily there to be used when this object is copied.
		protected WeaponCategory copyCategory = CapabilityItem.WeaponCategories.FIST;
		protected SoundEvent swingSoundCopy = EpicFightSounds.WHOOSH.get();
		protected SoundEvent hitSoundCopy = EpicFightSounds.BLUNT_HIT.get();
		protected HitParticleType hitParticleCopy = EpicFightParticles.HIT_BLADE.get();
		protected Collider colliderCopy = ColliderPreset.FIST;
		protected boolean offHandPlacementCopy = true;

		//Utilizing the getGuardMotion method in EXWeaponCapability, guardAnimations can define a custom set of guard animations for each guard skill and block type.
		protected final Map<Style, Map<GuardSkill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>> guardAnimations;

		protected Builder()
		{
			super();
            this.constructor(EXWeaponCapability::new);
			mountAttackAnimation = Maps.newHashMap();
			punishmentAnimation = Maps.newHashMap();
            this.shouldRenderSheath = Maps.newHashMap();
			battleModeAnimations = Maps.newHashMap();
			battleTransitionAnimations = Maps.newHashMap();
			castAnimations = Maps.newHashMap();
			chantAnimations = Maps.newHashMap();
			clashes = Maps.newHashMap();
			guardAnimations = Maps.newHashMap();
			weaponPassiveSkill = Maps.newHashMap();
            sheath = Maps.newHashMap();
		}

		@SafeVarargs
        public final void addMountAttacks(Style style, AnimationManager.AnimationAccessor<? extends AttackAnimation>... mountAttacks)
		{
			mountAttackAnimation.put(style, Arrays.asList(mountAttacks));
		}


		@SafeVarargs
        public final void addGuardMotion(Style wieldStyle, Skill guardSkill, GuardSkill.BlockType blockType, AnimationManager.AnimationAccessor<? extends StaticAnimation>... animation)
		{
			if (guardSkill instanceof GuardSkill skill)
			{
				guardAnimations.computeIfAbsent(wieldStyle, k -> Maps.newHashMap());
				guardAnimations.get(wieldStyle).computeIfAbsent(skill, k -> Maps.newHashMap());
				guardAnimations.get(wieldStyle).get(guardSkill).computeIfAbsent(blockType, k -> Lists.newArrayList());
				guardAnimations.get(wieldStyle).get(guardSkill).get(blockType).addAll(Arrays.asList(animation));
			}
		}

		public Builder addSheath(Item target, Item sheath)
		{
			this.sheath.put(target, sheath);
			return this;
		}

		public Builder addRevelationAnimation(Style wieldStyle, AnimationManager.AnimationAccessor<? extends AttackAnimation> animation)
		{
			punishmentAnimation.put(wieldStyle, animation);
			return this;
		}



		public Builder addTransitionAnimation(Style wieldStyle, AnimationManager.AnimationAccessor<? extends StaticAnimation> transitionAnimations)
		{
			battleTransitionAnimations.put(wieldStyle, transitionAnimations);
			return this;
		}

		@Override
		public Builder category(WeaponCategory category)
		{
			copyCategory = category;
			return (Builder) super.category(category);
		}

		@Override
		public Builder styleProvider(Function<LivingEntityPatch<?>, Style> styleProvider) {
			return (Builder) super.styleProvider(styleProvider);
		}

		@Override
		public Builder passiveSkill(Skill passiveSkill) {
			return (Builder) super.passiveSkill(passiveSkill);
		}

		@Override
		public Builder swingSound(SoundEvent swingSound) {
			swingSoundCopy = swingSound;
			return (Builder) super.swingSound(swingSound);
		}

		@Override
		public Builder hitSound(SoundEvent hitSound) {
			hitSoundCopy = hitSound;
			return (Builder) super.hitSound(hitSound);
		}

		@Override
		public Builder hitParticle(HitParticleType hitParticle) {
			hitParticleCopy = hitParticle;
			return (Builder) super.hitParticle(hitParticle);
		}

		@Override
		public Builder collider(Collider collider) {
			colliderCopy = collider;
			return (Builder) super.collider(collider);
		}

		@Override
		public Builder canBePlacedOffhand(boolean canBePlacedOffhand) {
			offHandPlacementCopy = canBePlacedOffhand;
			return (Builder) super.canBePlacedOffhand(canBePlacedOffhand);
		}

		public void exCapLMMs(Style wieldStyle, LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation) {
			livingMotionModifier(wieldStyle, livingMotion, animation);
		}

		@SafeVarargs
        public final void addClashAnimation(Style wieldStyle, ClashType clashType, AnimationManager.AnimationAccessor<? extends StaticAnimation>... animation)
		{
			clashes.computeIfAbsent(wieldStyle, k -> Maps.newHashMap()).computeIfAbsent(clashType, k -> Lists.newArrayList(animation));
		}

		@Override
		public Builder addStyleAttibutes(Style style, Holder<Attribute> attribute, AttributeModifier attributePair) {
			return (Builder) super.addStyleAttibutes(style, attribute, attributePair);
		}

		@Override
		public Builder weaponCombinationPredicator(Function<LivingEntityPatch<?>, Boolean> predicator) {
			return (Builder) super.weaponCombinationPredicator(predicator);
		}

		@Override
		public Builder innateSkill(Style style, Function<ItemStack, Skill> innateSkill) {
			return (Builder) super.innateSkill(style, innateSkill);
		}

		@Override
		public Builder comboCancel(Function<Style, Boolean> comboCancel) {
			return (Builder) super.comboCancel(comboCancel);
		}

		public void createStyleCategory(Style style, Function<Pair<Style, Builder>, Builder> weaponCombo)
		{
			weaponCombo.apply(new Pair<>(style, this));
		}

		@SuppressWarnings("unchecked")
		public void addMoveset(Style style, MoveSet moveSet)
		{
			newStyleCombo(style, moveSet.getAutoAttackAnimations().toArray(AnimationManager.AnimationAccessor[]::new));
			moveSet.getLivingMotionModifiers().forEach((motion, animation) -> exCapLMMs(style, motion, animation));
			moveSet.getGuardAnimations().forEach((guardSkill, blockTypeListMap) -> blockTypeListMap.forEach(((blockType, animationProviders) -> addGuardMotion(style, guardSkill, blockType, animationProviders.toArray(AnimationManager.AnimationAccessor[]::new)))));
			innateSkill(style, moveSet.getWeaponInnateSkill());
			weaponPassiveSkill.put(style, moveSet.getWeaponPassiveSkill());
			if (!moveSet.getMountAttackAnimations().isEmpty()) {
				addMountAttacks(style, moveSet.getMountAttackAnimations().toArray(AnimationManager.AnimationAccessor[]::new));
			}
			if (moveSet.getRevelation() != null)
			{
				addRevelationAnimation(style, moveSet.getRevelation());
			}
		}

		@Override
		public Builder zoomInType(ZoomInType zoomInType) {
			return (Builder) super.zoomInType(zoomInType);
		}
	}
}

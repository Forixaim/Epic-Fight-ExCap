package net.forixaim.efm_ex.capabilities.weaponcaps;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * A custom implementation of the original WeaponCapability class with a focus on modularity with a conditional passive skill provider.
 */
public class EXWeaponCapability extends WeaponCapability
{

	protected final Map<Style, Skill> weaponPassiveSkill;
	protected final Map<Style, Map<Object, AnimationProvider<?>>> chantAnimations;
	protected final Map<Style, Map<Object, AnimationProvider<?>>> castAnimations;
	protected final Map<Style, Map<LivingMotion, AnimationProvider<?>>> battleModeAnimations;
	protected final Map<Style, AnimationProvider<?>> battleTransitionAnimations;
	protected final Map<Style, Map<GuardSkill, Map<GuardSkill.BlockType, List<AnimationProvider<?>>>>> guardAnimations;
	protected final Map<Style, Map<LivingMotion, AnimationProvider<?>>> ExCapMotions;

	protected EXWeaponCapability(CapabilityItem.Builder builder)
	{
		super(builder);
		Builder efbsBuilder = (Builder) builder;
		this.battleTransitionAnimations = efbsBuilder.battleTransitionAnimations;
		this.battleModeAnimations = efbsBuilder.battleModeAnimations;
		this.castAnimations = efbsBuilder.castAnimations;
		this.chantAnimations = efbsBuilder.chantAnimations;
		this.guardAnimations = efbsBuilder.guardAnimations;
		this.weaponPassiveSkill = efbsBuilder.weaponPassiveSkill;
		this.ExCapMotions = efbsBuilder.ExCapLMMs;
	}

	public Map<Style, AnimationProvider<?>> getBattleTransitionAnimations()
	{
		return battleTransitionAnimations;
	}

	public Map<Style, Map<Object, AnimationProvider<?>>>  getCastAnimations()
	{
		return castAnimations;
	}

	public Map<Style, Map<Object, AnimationProvider<?>>>  getChantAnimations()
	{
		return chantAnimations;
	}

	@Override
	public void changeWeaponInnateSkill(PlayerPatch<?> playerpatch, ItemStack itemstack)
	{
		Skill weaponInnateSkill = this.getInnateSkill(playerpatch, itemstack);
		String skillName = "";
		SPChangeSkill.State state = SPChangeSkill.State.ENABLE;
		SkillContainer weaponInnateSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_INNATE);
		if (weaponInnateSkill != null) {
			if (weaponInnateSkillContainer.getSkill() != weaponInnateSkill) {
				weaponInnateSkillContainer.setSkill(weaponInnateSkill);
			}

			skillName = weaponInnateSkill.toString();
		} else {
			state = SPChangeSkill.State.DISABLE;
		}

		weaponInnateSkillContainer.setDisabled(weaponInnateSkill == null);
		EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_INNATE, skillName, state), (ServerPlayer)playerpatch.getOriginal());
		Skill skill = weaponPassiveSkill.get(this.getStyle(playerpatch));
		SkillContainer passiveSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE);
		if (skill != null) {
			if (passiveSkillContainer.getSkill() != skill) {
				passiveSkillContainer.setSkill(skill);
				EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, skill.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer)playerpatch.getOriginal());
			}
		} else {
			passiveSkillContainer.setSkill(null);
			EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, "empty", SPChangeSkill.State.ENABLE), (ServerPlayer)playerpatch.getOriginal());
		}
	}

	@Override
	public Map<LivingMotion, AnimationProvider<?>> getLivingMotionModifier(LivingEntityPatch<?> player, InteractionHand hand) {
		if (this.ExCapMotions != null && hand != InteractionHand.OFF_HAND) {
			Map<LivingMotion, AnimationProvider<?>> motions = this.ExCapMotions.getOrDefault(this.getStyle(player), Maps.newHashMap());
			Map<LivingMotion, AnimationProvider<?>> var10000 = this.ExCapMotions.getOrDefault(Styles.COMMON, Maps.newHashMap());
			Objects.requireNonNull(motions);
			var10000.forEach(motions::putIfAbsent);
			return motions;
		} else {
			return super.getLivingMotionModifier(player, hand);
		}
	}

	@Override
	public StaticAnimation getGuardMotion(GuardSkill skill, GuardSkill.BlockType blockType, PlayerPatch<?> playerpatch)
	{
		Style style = this.getStyle(playerpatch);
		if (guardAnimations.containsKey(style) && guardAnimations.get(style).containsKey(skill) && guardAnimations.get(style).get(skill).containsKey(blockType))
		{
			List<AnimationProvider<?>> animations = guardAnimations.get(style).get(skill).get(blockType);
			SkillDataManager dataManager = playerpatch.getSkill(skill).getDataManager();
			if (animations != null && !animations.isEmpty())
			{
				if (dataManager.hasData(SkillDataKeys.PARRY_MOTION_COUNTER.get()) && blockType == GuardSkill.BlockType.ADVANCED_GUARD)
				{
					int motionCounter = dataManager.getDataValue(SkillDataKeys.PARRY_MOTION_COUNTER.get());
					dataManager.setDataF(SkillDataKeys.PARRY_MOTION_COUNTER.get(), (v) -> v + 1);
					motionCounter %= animations.size();
					return animations.get(motionCounter).get();
				}
				return animations.get(playerpatch.getOriginal().getRandom().nextInt(animations.size())).get();
			}
		}
		return super.getGuardMotion(skill, blockType, playerpatch);
	}

	public static Builder builder() {
		return new Builder();
	}
	/**
	 * The modified builder that allows for easier weapon style creation and conditional passive skill providers.
	 */
	public static class Builder extends WeaponCapability.Builder
	{
		protected Function<LivingEntityPatch<?>, Skill> passiveSkillProvider;
		//The Object parameter is to be cast into CastType class
		protected final Map<Style, Map<Object, AnimationProvider<?>>>  castAnimations;
		protected final Map<Style, Map<Object, AnimationProvider<?>>>  chantAnimations;
		protected Map<Style, Map<LivingMotion, AnimationProvider<?>>> battleModeAnimations;
		protected final Map<Style, AnimationProvider<?>> battleTransitionAnimations;
		protected final Map<Style, Map<LivingMotion, AnimationProvider<?>>> ExCapLMMs;
		protected final Map<Style, Skill> weaponPassiveSkill;

		//Utilizing the getGuardMotion method in EXWeaponCapability, guardAnimations can define a custom set of guard animations for each guard skill and block type.
		protected final Map<Style, Map<GuardSkill, Map<GuardSkill.BlockType, List<AnimationProvider<?>>>>> guardAnimations;

		protected Builder()
		{
			super();
			this.constructor(EXWeaponCapability::new);
			battleModeAnimations = Maps.newHashMap();
			battleTransitionAnimations = Maps.newHashMap();
			castAnimations = Maps.newHashMap();
			chantAnimations = Maps.newHashMap();
			guardAnimations = Maps.newHashMap();
			weaponPassiveSkill = Maps.newHashMap();
			ExCapLMMs = Maps.newHashMap();
		}

		public void addGuardMotion(Style wieldStyle, GuardSkill guardSkill, GuardSkill.BlockType blockType, StaticAnimation... animation)
		{
			guardAnimations.computeIfAbsent(wieldStyle, k -> Maps.newHashMap());
			guardAnimations.get(wieldStyle).computeIfAbsent(guardSkill, k -> Maps.newHashMap());
			guardAnimations.get(wieldStyle).get(guardSkill).computeIfAbsent(blockType, k -> Lists.newArrayList());
			guardAnimations.get(wieldStyle).get(guardSkill).get(blockType).addAll(Arrays.asList(animation));
		}


		/**
		 *
		 * @param style
		 * @param castType must be a CastType class which will be cast into the respective class when onSpellCast.
		 * @param provider
		 * @return
		 */
		public Builder addCastAnimation(Style style, Object castType, AnimationProvider<?> provider)
		{
			castAnimations.computeIfAbsent(style, k -> Maps.newHashMap());
			if (ModList.get().isLoaded(IronsSpellbooks.MODID))
			{
				castAnimations.get(style).put(castType, provider);
			}
			return this;
		}

		public Builder addTransitionAnimation(Style wieldStyle, StaticAnimation transitionAnimations)
		{
			battleTransitionAnimations.put(wieldStyle, transitionAnimations);
			return this;
		}

		public Builder initialSetup(WeaponCategory category, SoundEvent swingSound, SoundEvent hitSound)
		{
			this.category(category);
			this.swingSound(swingSound);
			this.hitSound(hitSound);
			return this;
		}

		public Builder passiveProvider(Function<LivingEntityPatch<?>, Skill> passiveSkillProvider)
		{
			this.passiveSkillProvider = passiveSkillProvider;
			return this;
		}

		@Override
		public Builder constructor(Function<CapabilityItem.Builder, CapabilityItem> constructor) {
			return (Builder) super.constructor(constructor);
		}

		@Override
		public Builder category(WeaponCategory category) {
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
			return (Builder) super.swingSound(swingSound);
		}

		@Override
		public Builder hitSound(SoundEvent hitSound) {
			return (Builder) super.hitSound(hitSound);
		}

		@Override
		public Builder hitParticle(HitParticleType hitParticle) {
			return (Builder) super.hitParticle(hitParticle);
		}

		@Override
		public Builder collider(Collider collider) {
			return (Builder) super.collider(collider);
		}

		@Override
		public Builder canBePlacedOffhand(boolean canBePlacedOffhand) {
			return (Builder) super.canBePlacedOffhand(canBePlacedOffhand);
		}

		public void exCapLMMs(Style wieldStyle, LivingMotion livingMotion, AnimationProvider<?> animation) {
			ExCapLMMs.computeIfAbsent(wieldStyle, k -> Maps.newHashMap());
			ExCapLMMs.get(wieldStyle).put(livingMotion, animation);
		}

		@Override
		public Builder addStyleAttibutes(Style style, Pair<Attribute, AttributeModifier> attributePair) {
			return (Builder) super.addStyleAttibutes(style, attributePair);
		}

		@Override
		public Builder newStyleCombo(Style style, StaticAnimation... animation) {
			return (Builder) super.newStyleCombo(style, animation);
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

		public void addMoveset(Style style, MoveSet moveSet)
		{
			newStyleCombo(style, moveSet.getAutoAttackAnimations().toArray(StaticAnimation[]::new));
			moveSet.getLivingMotionModifiers().forEach((motion, animation) -> exCapLMMs(style, motion, () -> animation));
			moveSet.getGuardAnimations().forEach((guardSkill, blockTypeListMap) -> blockTypeListMap.forEach(((blockType, animationProviders) -> addGuardMotion(style, guardSkill, blockType, animationProviders.toArray(StaticAnimation[]::new)))));
			innateSkill(style, moveSet.getWeaponInnateSkill());
			weaponPassiveSkill.put(style, moveSet.getWeaponPassiveSkill());
		}
	}
}

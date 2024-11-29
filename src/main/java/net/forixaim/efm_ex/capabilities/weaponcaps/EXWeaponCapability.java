package net.forixaim.efm_ex.capabilities.weaponcaps;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.spells.CastType;
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
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A custom implementation of the original WeaponCapability class with a focus on modularity with a conditional passive skill provider.
 */
public class EXWeaponCapability extends WeaponCapability
{
	protected final Function<LivingEntityPatch<?>, Skill> passiveSkillProvider;
	protected final Map<Style, Map<Object, AnimationProvider<?>>> chantAnimations;
	protected final Map<Style, Map<Object, AnimationProvider<?>>> castAnimations;
	protected final Map<Style, Map<LivingMotion, AnimationProvider<?>>> battleModeAnimations;
	protected final Map<Style, AnimationProvider<?>> battleTransitionAnimations;

	protected EXWeaponCapability(CapabilityItem.Builder builder)
	{
		super(builder);
		Builder efbsBuilder = (Builder) builder;
		this.battleTransitionAnimations = efbsBuilder.battleTransitionAnimations;
		this.battleModeAnimations = efbsBuilder.battleModeAnimations;
		this.passiveSkillProvider = efbsBuilder.passiveSkillProvider;
		this.castAnimations = efbsBuilder.castAnimations;
		this.chantAnimations = efbsBuilder.chantAnimations;
	}

	@Override
	public Map<LivingMotion, AnimationProvider<?>> getLivingMotionModifier(LivingEntityPatch<?> player, InteractionHand hand)
	{
		if (this.battleModeAnimations != null && !this.battleModeAnimations.isEmpty())
		{
			if (player instanceof PlayerPatch<?> playerPatch && playerPatch.isBattleMode())
			{
				Map<LivingMotion, AnimationProvider<?>> motions = this.battleModeAnimations.getOrDefault(this.getStyle(player), Maps.newHashMap());
				Map<LivingMotion, AnimationProvider<?>> commonMotions = this.battleModeAnimations.getOrDefault(Styles.COMMON, Maps.newHashMap());
				Objects.requireNonNull(motions);
				commonMotions.forEach(motions::putIfAbsent);
				return motions;
			}
		}
		return super.getLivingMotionModifier(player, hand);
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
		Skill skill = getPassiveProvider().apply(playerpatch);
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

	public Function<LivingEntityPatch<?>, Skill> getPassiveProvider()
	{
		return passiveSkillProvider;
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

		protected Builder()
		{
			super();
			this.constructor(EXWeaponCapability::new);
			battleModeAnimations = Maps.newHashMap();
			battleTransitionAnimations = Maps.newHashMap();
			castAnimations = Maps.newHashMap();
			chantAnimations = Maps.newHashMap();
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

		@Override
		public Builder livingMotionModifier(Style wieldStyle, LivingMotion livingMotion, StaticAnimation animation) {
			return (Builder) super.livingMotionModifier(wieldStyle, livingMotion, animation);
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
	}
}

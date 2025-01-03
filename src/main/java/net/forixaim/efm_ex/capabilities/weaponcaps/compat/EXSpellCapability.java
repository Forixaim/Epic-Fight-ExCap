package net.forixaim.efm_ex.capabilities.weaponcaps.compat;

import com.google.common.collect.Maps;
import com.mna.api.spells.parts.Shape;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.moveset.CastingMoveSet;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.Map;
import java.util.function.Function;

public class EXSpellCapability extends EXWeaponCapability
{
	private Map<Shape, AnimationProvider<?>> castAnimations;
	
	public EXSpellCapability(CapabilityItem.Builder builder)
	{
		super(builder);
		castAnimations = ((Builder)builder).castAnimations;
	}

	public Map<Shape, AnimationProvider<?>> getCastAnimations(LivingEntityPatch<?> entityPatch)
	{
		return castAnimations;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder extends EXWeaponCapability.Builder
	{
		protected final Map<Shape, AnimationProvider<?>> castAnimations;

		public Builder()
		{
			super();
			constructor(EXSpellCapability::new);
			castAnimations = Maps.newHashMap();
		}

		public Builder addTransitionAnimation(Style wieldStyle, StaticAnimation transitionAnimations)
		{
			battleTransitionAnimations.put(wieldStyle, transitionAnimations);
			return this;
		}

		public Builder battleMotionModifier(Style wieldStyle, LivingMotion livingMotion, StaticAnimation animation) {
			if (this.battleModeAnimations == null) {
				this.battleModeAnimations = Maps.newHashMap();
			}

			if (!this.battleModeAnimations.containsKey(wieldStyle)) {
				this.battleModeAnimations.put(wieldStyle, Maps.newHashMap());
			}

			this.battleModeAnimations.get(wieldStyle).put(livingMotion, animation);
			return this;
		}

		public Builder addMNACastAnim(Style style, Shape shape, AnimationProvider<?> animation)
		{
			if (!castAnimations.containsKey(shape))
			{
				castAnimations.put(shape, animation);
			}
			return this;
		}

		public Builder addMNACastAnimations(Style style, Function<Pair<Style, EXSpellCapability.Builder>, EXSpellCapability.Builder> castAnimations)
		{
			return castAnimations.apply(Pair.of(style, this));
		}
		@Override
		public Builder initialSetup(WeaponCategory category, SoundEvent swingSound, SoundEvent hitSound)
		{
			this.category(category);
			this.swingSound(swingSound);
			this.hitSound(hitSound);
			return this;
		}

		@Override
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
		public void addMoveset(Style style, MoveSet moveSet)
		{
			super.addMoveset(style, moveSet);
			if (moveSet instanceof CastingMoveSet cms)
			{
                castAnimations.putAll(cms.getSpellAnimations());
			}
		}

		@Override
		public Builder comboCancel(Function<Style, Boolean> comboCancel) {
			return (Builder) super.comboCancel(comboCancel);
		}

	}
}

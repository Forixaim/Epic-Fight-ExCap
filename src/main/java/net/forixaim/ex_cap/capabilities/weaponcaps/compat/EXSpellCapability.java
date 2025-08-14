package net.forixaim.ex_cap.capabilities.weaponcaps.compat;

import com.google.common.collect.Maps;
import com.mna.api.spells.parts.Shape;
import com.mojang.datafixers.util.Pair;
import net.forixaim.ex_cap.api.moveset.CastingMoveSet;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
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
	private Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> castAnimations;
	
	public EXSpellCapability(CapabilityItem.Builder builder)
	{
		super(builder);
		castAnimations = ((Builder)builder).castAnimations;
	}

	public Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getCastAnimations(LivingEntityPatch<?> entityPatch)
	{
		return castAnimations;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder extends EXWeaponCapability.Builder
	{
		protected final Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> castAnimations;

		public Builder()
		{
			super();
			constructor(EXSpellCapability::new);
			castAnimations = Maps.newHashMap();
		}

		public Builder addTransitionAnimation(Style wieldStyle, AnimationManager.AnimationAccessor<? extends StaticAnimation> transitionAnimations)
		{
			battleTransitionAnimations.put(wieldStyle, transitionAnimations);
			return this;
		}

		public Builder battleMotionModifier(Style wieldStyle, LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation) {
			if (this.battleModeAnimations == null) {
				this.battleModeAnimations = Maps.newHashMap();
			}

			if (!this.battleModeAnimations.containsKey(wieldStyle)) {
				this.battleModeAnimations.put(wieldStyle, Maps.newHashMap());
			}

			this.battleModeAnimations.get(wieldStyle).put(livingMotion, animation);
			return this;
		}

		public Builder addMNACastAnim(Style style, Shape shape, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation)
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
		public Builder livingMotionModifier(Style wieldStyle, LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation) {
			return (Builder) super.livingMotionModifier(wieldStyle, livingMotion, animation);
		}

		@Override
		public Builder addStyleAttibutes(Style style, Pair<Attribute, AttributeModifier> attributePair) {
			return (Builder) super.addStyleAttibutes(style, attributePair);
		}

		@SafeVarargs
        public final Builder newSpellStyleCombo(Style style, AnimationManager.AnimationAccessor<? extends AttackAnimation>... animation) {
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

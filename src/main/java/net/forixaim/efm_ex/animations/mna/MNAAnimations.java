package net.forixaim.efm_ex.animations.mna;

import net.forixaim.efm_ex.api.animation.events.CastSpell;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;

public class MNAAnimations
{
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> SPELL_AUTO1;

	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> SPELL_AUTO2;

	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> SPELL_AUTO3;

	public static void Build(AnimationManager.AnimationBuilder event)
	{
		MNAAnimations.SPELL_AUTO1 = event.nextAccessor("spell/auto1", access -> new BasicAttackAnimation(0.3f, 0.0f, 0.5f, 0.55f, 0.6f, ColliderPreset.FIST, Armatures.BIPED.get().toolR, access, Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.InTimeEvent.create(0.5f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER)));

		MNAAnimations.SPELL_AUTO1 = event.nextAccessor("spell/auto2", access -> new BasicAttackAnimation(
				0.2f, 0.0f, 0.25f, 0.3f, 0.6f, ColliderPreset.FIST, Armatures.BIPED.get().toolR, access, Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.InTimeEvent.create(0.25f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER)));

		MNAAnimations.SPELL_AUTO1 = event.nextAccessor("spell/auto3", access -> new BasicAttackAnimation(
				0.2f, 0.0f, 0.9f, 0.95f, 1f, ColliderPreset.FIST, Armatures.BIPED.get().toolR, access, Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.InTimeEvent.create(0.9f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER)));

	}
}

package net.forixaim.efm_ex.animations.mna;

import net.forixaim.efm_ex.api.animation.events.CastSpell;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;

public class MNAAnimations
{
	public static StaticAnimation SPELL_AUTO1;

	public static StaticAnimation SPELL_AUTO2;

	public static StaticAnimation SPELL_AUTO3;

	public static void Build()
	{
		HumanoidArmature biped = Armatures.BIPED;

		SPELL_AUTO1 = new BasicAttackAnimation(0.3f, 0.0f, 0.5f, 0.55f, 0.6f, ColliderPreset.FIST, biped.toolR, "spell/auto1", biped)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.5f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER));

		SPELL_AUTO2 = new BasicAttackAnimation(0.2f, 0.0f, 0.25f, 0.3f, 0.6f, ColliderPreset.FIST, biped.toolR, "spell/auto2", biped)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.25f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER));

		SPELL_AUTO3 = new BasicAttackAnimation(0.2f, 0.0f, 0.9f, 0.95f, 1f, ColliderPreset.FIST, biped.toolR, "spell/auto3", biped)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3f)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.9f, CastSpell.castMNASpell, AnimationEvent.Side.SERVER));

	}
}

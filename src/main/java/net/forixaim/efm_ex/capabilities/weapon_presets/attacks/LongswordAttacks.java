package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.LongswordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class LongswordAttacks
{

	public static void injectAttacks()
	{
		LongswordType.getInstance().getAttackSets().add(LongswordAttacks.THLongsword);
		LongswordType.getInstance().getAttackSets().add(LongswordAttacks.OHLongsword);
		LongswordType.getInstance().getAttackSets().add(LongswordAttacks.Liechtenauer);
		LongswordType.getInstance().getAttackSets().add(MountedAttacks.SwordMountMoveset);

	}

	public static final MoveSet THLongsword = MoveSet.createMoveSet(CapabilityItem.Styles.TWO_HAND)
			.addAttackAnimation(Animations.LONGSWORD_AUTO1,
					Animations.LONGSWORD_AUTO2,
					Animations.LONGSWORD_AUTO3,
					Animations.LONGSWORD_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addLMM(LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD)
			.addLMM(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
			.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
			.addLMMRecursive(Animations.BIPED_HOLD_LONGSWORD,
					LivingMotions.IDLE,
					LivingMotions.SNEAK,
					LivingMotions.KNEEL,
					LivingMotions.JUMP,
					LivingMotions.SWIM
			)
			.addLMM(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.innateSkill(itemStack -> EpicFightSkills.LIECHTENAUER)
			;

	public static final MoveSet OHLongsword = MoveSet.createMoveSet(CapabilityItem.Styles.ONE_HAND)
			.addAttackAnimation(Animations.LONGSWORD_AUTO1,
					Animations.LONGSWORD_AUTO2,
					Animations.LONGSWORD_AUTO3,
					Animations.LONGSWORD_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addLMM(LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD)
			.addLMM(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD)
			.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
			.addLMMRecursive(Animations.BIPED_HOLD_LONGSWORD,
					LivingMotions.IDLE,
					LivingMotions.SNEAK,
					LivingMotions.KNEEL,
					LivingMotions.JUMP,
					LivingMotions.SWIM
			)
			.addLMM(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.innateSkill(itemStack -> EpicFightSkills.SHARP_STAB)
			;

	public static final MoveSet Liechtenauer = MoveSet.createMoveSet(CapabilityItem.Styles.OCHS)
			.addAttackAnimation(Animations.LONGSWORD_LIECHTENAUER_AUTO1,
					Animations.LONGSWORD_LIECHTENAUER_AUTO2,
					Animations.LONGSWORD_LIECHTENAUER_AUTO3,
					Animations.LONGSWORD_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addLMM(LivingMotions.CHASE, Animations.BIPED_WALK_LIECHTENAUER)
			.addLMM(LivingMotions.WALK, Animations.BIPED_WALK_LIECHTENAUER)
			.addLMMRecursive(Animations.BIPED_HOLD_LIECHTENAUER,
					LivingMotions.IDLE,
					LivingMotions.SNEAK,
					LivingMotions.KNEEL,
					LivingMotions.JUMP,
					LivingMotions.SWIM,
					LivingMotions.RUN
			)
			.addLMM(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.innateSkill(itemStack -> EpicFightSkills.LIECHTENAUER)
			;
}

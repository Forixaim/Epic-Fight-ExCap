package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.LongswordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class LongswordAttacks
{

	public static void injectAttacks()
	{
		LongswordType.getInstance().getAttackSets().put(CapabilityItem.Styles.OCHS, LiechtenauerMS);
		LongswordType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, longsword1HMS);
		LongswordType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, longsword2HMS);
		LongswordType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordMS);

	}

	public static final MoveSet longsword2HMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_LONGSWORD,
					LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
					LivingMotions.JUMP, LivingMotions.SWIM)
			.addLivingMotionsRecursive(Animations.BIPED_WALK_LONGSWORD,
					LivingMotions.WALK, LivingMotions.CHASE)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.addAutoAttacks(
					Animations.LONGSWORD_AUTO1,
					Animations.LONGSWORD_AUTO2,
					Animations.LONGSWORD_AUTO3,
					Animations.LONGSWORD_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.LIECHTENAUER)
			.build();

	public static final MoveSet longsword1HMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_LONGSWORD,
					LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
					LivingMotions.JUMP, LivingMotions.SWIM)
			.addLivingMotionsRecursive(Animations.BIPED_WALK_LONGSWORD,
					LivingMotions.WALK, LivingMotions.CHASE)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.addAutoAttacks(
					Animations.LONGSWORD_AUTO1,
					Animations.LONGSWORD_AUTO2, Animations.LONGSWORD_AUTO3,
					Animations.LONGSWORD_DASH, Animations.LONGSWORD_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.SHARP_STAB)
			.build();

	public static final MoveSet LiechtenauerMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_LIECHTENAUER,
					LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
					LivingMotions.JUMP, LivingMotions.SWIM)
			.addLivingMotionsRecursive(Animations.BIPED_WALK_LIECHTENAUER,
					LivingMotions.WALK, LivingMotions.CHASE)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_HOLD_LIECHTENAUER)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.addAutoAttacks(
					Animations.LONGSWORD_LIECHTENAUER_AUTO1,
					Animations.LONGSWORD_LIECHTENAUER_AUTO2,
					Animations.LONGSWORD_LIECHTENAUER_AUTO3,
					Animations.LONGSWORD_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.SHARP_STAB)
			.build();
}

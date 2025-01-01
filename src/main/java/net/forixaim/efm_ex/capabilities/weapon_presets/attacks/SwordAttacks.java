package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SwordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class SwordAttacks
{
	public static final MoveSet sword1HMS = MoveSet.builder()
			.addAutoAttacks(
					Animations.SWORD_AUTO1,
					Animations.SWORD_AUTO2, Animations.SWORD_AUTO3,
					Animations.SWORD_DASH, Animations.SWORD_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.SWEEPING_EDGE)
			.build();

	public static final MoveSet sword2HMS = MoveSet.builder()
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_DUAL_WEAPON,
					LivingMotions.IDLE, LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE,
					LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
			.addAutoAttacks(
					Animations.SWORD_DUAL_AUTO1,
					Animations.SWORD_DUAL_AUTO2, Animations.SWORD_DUAL_AUTO3,
					Animations.SWORD_DUAL_DASH, Animations.SWORD_DUAL_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.DANCING_EDGE)
			.build();

	public static void injectAttacks()
	{
		SwordType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, SwordAttacks.sword1HMS);
		SwordType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, SwordAttacks.sword2HMS);
		SwordType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordMS);
	}
}

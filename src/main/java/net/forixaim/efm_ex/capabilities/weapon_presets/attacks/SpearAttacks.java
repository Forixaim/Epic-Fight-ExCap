package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SpearType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class SpearAttacks
{
	public static void injectAttacks()
	{
		SpearType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, spear1HMS);
		SpearType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, spear2HMS);
		SpearType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSpearMS);
	}
	public static final MoveSet spear2HMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_SPEAR,
					LivingMotions.IDLE, LivingMotions.SWIM)
			.addLivingMotionsRecursive(Animations.BIPED_WALK_SPEAR,
					LivingMotions.WALK, LivingMotions.CHASE)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
			.addAutoAttacks(
					Animations.SPEAR_TWOHAND_AUTO1, Animations.SPEAR_TWOHAND_AUTO2,
					Animations.SPEAR_DASH,
					Animations.SPEAR_TWOHAND_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.GRASPING_SPIRE)
			.build();

	public static final MoveSet spear1HMS = MoveSet.builder()
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
			.addAutoAttacks(
					Animations.SPEAR_ONEHAND_AUTO,
					Animations.SPEAR_DASH,
					Animations.SPEAR_ONEHAND_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.HEARTPIERCER)
			.build();
}

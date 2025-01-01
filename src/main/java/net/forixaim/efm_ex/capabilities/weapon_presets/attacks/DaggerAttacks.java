package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.DaggerType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class DaggerAttacks
{
	public static void injectAttacks()
	{
		DaggerType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, DaggerAttacks.dagger2HMS);
		DaggerType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, DaggerAttacks.dagger1HMS);
		DaggerType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordMS);

	}

	public static final MoveSet dagger1HMS = MoveSet.builder()
			.addAutoAttacks(
					Animations.DAGGER_AUTO1,
					Animations.DAGGER_AUTO2,
					Animations.DAGGER_AUTO3,
					Animations.DAGGER_DASH,
					Animations.DAGGER_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.EVISCERATE)
			.build();

	public static final MoveSet dagger2HMS = MoveSet.builder()
			.addAutoAttacks(
					Animations.DAGGER_DUAL_AUTO1,
					Animations.DAGGER_DUAL_AUTO2,
					Animations.DAGGER_DUAL_AUTO3,
					Animations.DAGGER_DUAL_AUTO4,
					Animations.DAGGER_DUAL_DASH,
					Animations.DAGGER_DUAL_AIR_SLASH
			)
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_DUAL_WEAPON,
					LivingMotions.IDLE, LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE,
					LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
			.addInnateSkill(itemStack -> EpicFightSkills.BLADE_RUSH)
			.build();
}

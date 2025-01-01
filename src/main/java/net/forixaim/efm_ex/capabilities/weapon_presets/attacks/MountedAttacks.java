package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import yesman.epicfight.gameasset.Animations;

public class MountedAttacks
{
	public static final MoveSet mountedSwordMS = MoveSet.builder()
			.addAutoAttacks(
					Animations.SWORD_MOUNT_ATTACK
			)
			.build();

	public static final MoveSet mountedSpearMS = MoveSet.builder()
			.addAutoAttacks(
					Animations.SPEAR_MOUNT_ATTACK
			)
			.build();
}

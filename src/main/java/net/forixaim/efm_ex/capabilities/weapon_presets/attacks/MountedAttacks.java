package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class MountedAttacks
{
	public static final MoveSet SwordMountMoveset = MoveSet.createMoveSet(CapabilityItem.Styles.MOUNT)
			.addAttackAnimation(Animations.SWORD_MOUNT_ATTACK);

	public static final MoveSet SpearMountMoveset = MoveSet.createMoveSet(CapabilityItem.Styles.MOUNT)
			.addAttackAnimation(Animations.SPEAR_MOUNT_ATTACK);

	public static Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> mountedSwordAttack = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.newStyleCombo(style,
				Animations.SWORD_MOUNT_ATTACK
		);
		return builder;
	};

	public static Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> mountedSpearAttack = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.newStyleCombo(style,
				Animations.SPEAR_MOUNT_ATTACK
		);
		return builder;
	};
}

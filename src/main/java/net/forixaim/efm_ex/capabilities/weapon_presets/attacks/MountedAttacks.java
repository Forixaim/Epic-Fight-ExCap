package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.EXWeaponCapability;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class MountedAttacks
{
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

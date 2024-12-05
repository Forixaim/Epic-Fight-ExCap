package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.BowType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXBowWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXRangedWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class BowAttacks
{
	public static void Inject()
	{
		BowType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(
				CapabilityItem.Styles.TWO_HAND, defaultTwoHandAttackCycle
		));
	}

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main) ->
	{
		EXRangedWeaponCapability.Builder builder = (EXRangedWeaponCapability.Builder) main.getSecond();
		builder.zoomInType(CapabilityItem.ZoomInType.USE_TICK)
				.addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
				.addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK)
				.addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
				.addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
				.constructor(EXBowWeaponCapability::new);

		return main.getSecond();
	};
}

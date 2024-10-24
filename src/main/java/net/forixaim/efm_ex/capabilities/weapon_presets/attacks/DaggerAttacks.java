package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.DaggerType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class DaggerAttacks
{
	public static void injectAttacks()
	{
		DaggerType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, DaggerAttacks.defaultOneHandAttackCycle));
		DaggerType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, DaggerAttacks.defaultTwoHandAttackCycle));
		DaggerType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordAttack));

	}

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultOneHandAttackCycle = (main) ->
	{
		main.getSecond().newStyleCombo(main.getFirst(), Animations.DAGGER_AUTO1, Animations.DAGGER_AUTO2, Animations.DAGGER_AUTO3, Animations.DAGGER_DASH, Animations.DAGGER_AIR_SLASH)
				.innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> EpicFightSkills.EVISCERATE);
		return main.getSecond();
	};

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main) ->
	{
		main.getSecond().newStyleCombo(main.getFirst(), Animations.DAGGER_DUAL_AUTO1, Animations.DAGGER_DUAL_AUTO2, Animations.DAGGER_DUAL_AUTO3, Animations.DAGGER_DUAL_AUTO4, Animations.DAGGER_DUAL_DASH, Animations.DAGGER_DUAL_AIR_SLASH).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.KNEEL, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
			.livingMotionModifier(main.getFirst(), LivingMotions.SNEAK, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.SWIM, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.FLOAT, Animations.BIPED_HOLD_DUAL_WEAPON)
			.livingMotionModifier(main.getFirst(), LivingMotions.FALL, Animations.BIPED_HOLD_DUAL_WEAPON)
				.innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.BLADE_RUSH);

		return main.getSecond();
	};
}

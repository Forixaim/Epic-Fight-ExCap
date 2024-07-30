package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SpearType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class SpearAttacks
{
	public static final void injectAttacks()
	{
		SpearType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, SpearAttacks.defaultTwoHandAttackCycle));
		SpearType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, SpearAttacks.defaultOneHandAttackCycle));
		SpearType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSpearAttack));
	}
	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main -> {
			EXWeaponCapability.Builder builder = main.getSecond();
			Style style = main.getFirst();
			builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_SPEAR)
					.newStyleCombo(style, Animations.SPEAR_TWOHAND_AUTO1, Animations.SPEAR_TWOHAND_AUTO2, Animations.SPEAR_DASH, Animations.SPEAR_TWOHAND_AIR_SLASH)
					.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_WALK_SPEAR)
					.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_WALK_SPEAR)
					.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
					.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_SPEAR)
					.livingMotionModifier(style, LivingMotions.BLOCK, Animations.SPEAR_GUARD)
					.innateSkill(style, (itemstack) -> EpicFightSkills.GRASPING_SPIRE);
			return builder;
	});

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultOneHandAttackCycle = (main -> {
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.SPEAR_ONEHAND_AUTO, Animations.SPEAR_DASH, Animations.SPEAR_ONEHAND_AIR_SLASH)
				.livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
				.innateSkill(style, (itemstack) -> EpicFightSkills.HEARTPIERCER);
		return builder;
	});
}

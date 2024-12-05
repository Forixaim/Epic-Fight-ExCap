package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.AxeType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class AxeAttacks
{
	public static void injectAttacks()
	{
		AxeType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, defaultOneHandAttackCycle));
	}

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultOneHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.SWORD_GUARD);
		builder.newStyleCombo(style,
				Animations.AXE_AUTO1,
				Animations.AXE_AUTO2,
				Animations.AXE_DASH,
				Animations.AXE_AIRSLASH);
		builder.innateSkill(style, (itemStack -> EpicFightSkills.GUILLOTINE_AXE));
		return builder;
	};

}

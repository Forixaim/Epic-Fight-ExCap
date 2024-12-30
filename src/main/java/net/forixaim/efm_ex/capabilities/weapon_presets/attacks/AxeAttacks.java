package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.capabilities.movesets.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.AxeType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class AxeAttacks
{
	public static void injectAttacks()
	{
		AxeType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, axeOneHandMS);
	}

	public static final MoveSet axeOneHandMS = MoveSet.builder()
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.SWORD_GUARD)
			.addAutoAttacks(
					Animations.AXE_AUTO1,
					Animations.AXE_AUTO2,
					Animations.AXE_DASH,
					Animations.AXE_AIRSLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.GUILLOTINE_AXE)
			.build();

}

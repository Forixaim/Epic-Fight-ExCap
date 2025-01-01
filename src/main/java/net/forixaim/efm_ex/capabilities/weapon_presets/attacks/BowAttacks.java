package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.api.moveset.RangedMoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.BowType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class BowAttacks
{
	public static void Inject()
	{
		BowType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, rangedMoveSet);
	}

	public static final MoveSet rangedMoveSet = RangedMoveSet.builder()
			.addRangedAttackModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
			.addRangedAttackModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
			.addRangedAttackModifier(LivingMotions.WALK, Animations.BIPED_WALK)
			.addRangedAttackModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
			.addAutoAttacks(Animations.FIST_AUTO1, Animations.FIST_AUTO2, Animations.FIST_AUTO3, Animations.FIST_DASH, Animations.FIST_AIR_SLASH)
			.build();

}

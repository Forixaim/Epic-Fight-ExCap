package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.GreatswordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class GreatswordAttacks
{
	public static void injectAttacks()
	{
		GreatswordType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, greatsword2HMS);
	}

	private static final MoveSet greatsword2HMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_GREATSWORD,
					LivingMotions.IDLE, LivingMotions.JUMP, LivingMotions.KNEEL, LivingMotions.SNEAK,
					LivingMotions.SWIM, LivingMotions.FLY, LivingMotions.CREATIVE_FLY, LivingMotions.CREATIVE_IDLE)
			.addLivingMotionsRecursive(Animations.BIPED_WALK_GREATSWORD,
					LivingMotions.WALK,
					LivingMotions.CHASE)
			.addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
			.addAutoAttacks(
					Animations.GREATSWORD_AUTO1,
					Animations.GREATSWORD_AUTO2,
					Animations.GREATSWORD_DASH,
					Animations.GREATSWORD_AIR_SLASH
			)
			.addInnateSkill(itemstack -> EpicFightSkills.STEEL_WHIRLWIND)
			.build();
}

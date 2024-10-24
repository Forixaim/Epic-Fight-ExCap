package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.TachiType;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SpearType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class SpearAttacks
{
	public static void injectAttacks()
	{
		SpearType.getInstance().getAttackSets().add(TwoHSpear);
		SpearType.getInstance().getAttackSets().add(OneHSpear);
		SpearType.getInstance().getAttackSets().add(MountedAttacks.SpearMountMoveset);
	}

	public static final MoveSet TwoHSpear = MoveSet.createMoveSet(CapabilityItem.Styles.TWO_HAND)
			.addLMM(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
			.addLMM(LivingMotions.WALK, Animations.BIPED_WALK_SPEAR)
			.addLMM(LivingMotions.CHASE, Animations.BIPED_WALK_SPEAR)
			.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
			.addLMM(LivingMotions.IDLE, Animations.BIPED_HOLD_SPEAR)
			.addLMM(LivingMotions.SWIM, Animations.BIPED_HOLD_SPEAR)
			.addAttackAnimation(Animations.SPEAR_TWOHAND_AUTO1, Animations.SPEAR_TWOHAND_AUTO2, Animations.SPEAR_DASH, Animations.SPEAR_TWOHAND_AIR_SLASH)
			.innateSkill(itemstack -> EpicFightSkills.GRASPING_SPIRE);
	public static final MoveSet OneHSpear = MoveSet.createMoveSet(CapabilityItem.Styles.ONE_HAND)
			.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
			.innateSkill(itemStack -> EpicFightSkills.HEARTPIERCER)
			.addAttackAnimation(Animations.SPEAR_ONEHAND_AUTO, Animations.SPEAR_DASH, Animations.SPEAR_ONEHAND_AIR_SLASH);
}

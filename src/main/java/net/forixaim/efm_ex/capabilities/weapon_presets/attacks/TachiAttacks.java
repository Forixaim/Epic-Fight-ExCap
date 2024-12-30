package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.capabilities.movesets.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.TachiType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class TachiAttacks
{
	public static void injectAttacks()
	{
		TachiType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, tachi2HMS);
		TachiType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordMS);

	}

	public static final MoveSet tachi2HMS = MoveSet.builder()
			.addLivingMotionsRecursive(Animations.BIPED_HOLD_TACHI,
					LivingMotions.IDLE,
					LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE, LivingMotions.RUN,
					LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
			.addLivingMotionModifier(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD)
			.addAutoAttacks(
					Animations.TACHI_AUTO1,
					Animations.TACHI_AUTO2,
					Animations.TACHI_AUTO3,
					Animations.TACHI_DASH,
					Animations.LONGSWORD_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.RUSHING_TEMPO)
			.build();
}

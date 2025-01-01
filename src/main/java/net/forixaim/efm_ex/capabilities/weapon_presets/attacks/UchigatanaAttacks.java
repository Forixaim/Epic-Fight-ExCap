package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.UchigatanaType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class UchigatanaAttacks
{

    public static void injectAttacks()
    {
        UchigatanaType.getInstance().getAttackSets().put(CapabilityItem.Styles.SHEATH, UchigatanaSheathed);
        UchigatanaType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, UchigatanaBase);
        UchigatanaType.getInstance().getAttackSets().put(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordMS);
    }
    public static final MoveSet UchigatanaBase = MoveSet.builder()
            .addAutoAttacks(Animations.UCHIGATANA_AUTO1, Animations.UCHIGATANA_AUTO2,
                    Animations.UCHIGATANA_AUTO3, Animations.UCHIGATANA_DASH,
                    Animations.UCHIGATANA_AIR_SLASH)
            .addLivingMotionsRecursive(Animations.BIPED_HOLD_UCHIGATANA,
                    LivingMotions.IDLE, LivingMotions.KNEEL,
                    LivingMotions.SWIM, LivingMotions.FALL,
                    LivingMotions.FALL)
            .addLivingMotionsRecursive(Animations.BIPED_WALK_UCHIGATANA,
                    LivingMotions.CHASE, LivingMotions.WALK, LivingMotions.SNEAK)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA)
            .addLivingMotionModifier(LivingMotions.BLOCK, Animations.UCHIGATANA_GUARD)
            .addInnateSkill(itemStack -> EpicFightSkills.BATTOJUTSU)
            .setPassiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE)
            .build();

    public static final MoveSet UchigatanaSheathed = MoveSet.builder()
            .addAutoAttacks(Animations.UCHIGATANA_SHEATHING_AUTO,
                    Animations.UCHIGATANA_SHEATHING_DASH,
                    Animations.UCHIGATANA_SHEATH_AIR_SLASH)
            .addLivingMotionsRecursive(Animations.BIPED_HOLD_UCHIGATANA_SHEATHING,
                    LivingMotions.IDLE, LivingMotions.KNEEL,
                    LivingMotions.SWIM, LivingMotions.FALL,
                    LivingMotions.FALL, LivingMotions.SNEAK, LivingMotions.CHASE)
            .addLivingMotionModifier(LivingMotions.WALK, Animations.BIPED_WALK_UCHIGATANA_SHEATHING)
            .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA_SHEATHING)
            .addInnateSkill(itemStack -> EpicFightSkills.BATTOJUTSU)
            .setPassiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE)
            .build();
}

package net.forixaim.efm_ex.events;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.skill.identity.RevelationSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID)
public class EntityEvents
{
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event)
    {
        LivingEntityPatch<?> attackerPatch = EpicFightCapabilities.getEntityPatch(event.getSource().getEntity(), LivingEntityPatch.class);
        LivingEntityPatch<?> victimPatch = EpicFightCapabilities.getEntityPatch(event.getEntity(), LivingEntityPatch.class);

        if (attackerPatch != null && victimPatch != null)
        {
            if (attackerPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability attackerWeapon && victimPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability victimWeapon && attackerPatch.getAnimator().getEntityState().attacking() && victimPatch.getAnimator().getEntityState().attacking())
            {
                float damage = event.getAmount();
                if (attackerWeapon.getClashAnimation(EXWeaponCapability.ClashType.BLADE, attackerPatch) != null)
                {
                    attackerPatch.playAnimationSynchronized(attackerWeapon.getClashAnimation(EXWeaponCapability.ClashType.BLADE, attackerPatch), 0);
                }
                damage *= 0.3f;
                event.setAmount(damage);
            }
        }
    }
}

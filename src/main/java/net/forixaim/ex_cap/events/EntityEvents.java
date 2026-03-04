package net.forixaim.ex_cap.events;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.world.InteractionHand;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@EventBusSubscriber(modid = EpicFightEXCapability.MODID)
public class EntityEvents
{
    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event)
    {
        LivingEntityPatch<?> attackerPatch = EpicFightCapabilities.getEntityPatch(event.getSource().getEntity(), LivingEntityPatch.class);
        LivingEntityPatch<?> victimPatch = EpicFightCapabilities.getEntityPatch(event.getEntity(), LivingEntityPatch.class);

        if (attackerPatch != null && victimPatch != null)
        {
            if (attackerPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability attackerWeapon &&
                    victimPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND) instanceof EXWeaponCapability victimWeapon &&
                    attackerPatch.getAnimator().getEntityState().attacking() && victimPatch.getAnimator().getEntityState().attacking())
            {
                float damage = event.getOriginalDamage();
                damage *= 0.3f;
                event.setNewDamage(damage);
            }
        }
    }
}

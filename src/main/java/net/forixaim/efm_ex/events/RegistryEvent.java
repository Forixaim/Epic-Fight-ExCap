package net.forixaim.efm_ex.events;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvent
{
    @SubscribeEvent
    public static void addRenderers(PatchedRenderersEvent.Add event)
    {
    }
}

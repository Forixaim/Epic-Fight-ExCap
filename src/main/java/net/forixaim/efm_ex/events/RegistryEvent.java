package net.forixaim.efm_ex.events;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.client.renderers.SheathRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.world.item.EpicFightItems;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvent
{
    @SubscribeEvent
    public static void addRenderers(PatchedRenderersEvent.Add event)
    {
        event.addItemRenderer(EpicFightItems.IRON_LONGSWORD.get(), new SheathRenderers());
    }
}

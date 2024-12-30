package net.forixaim.efm_ex.api.events;

import com.google.common.collect.Maps;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.movesets.MoveSet;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Map;

public class MoveSetRegistryEvent extends Event implements IModBusEvent
{
    public Map<CoreCapability, Map<Style, MoveSet>> MoveSetRegistryMap;
    public MoveSetRegistryEvent() {
        MoveSetRegistryMap = Maps.newHashMap();
    }

    public void register()
    {
        MoveSetRegistryMap.entrySet().forEach((entry ->
                entry.getValue().forEach(
                        (style, moveSet) -> entry.getKey().getAttackSets().put(style, moveSet)
                )));
    }

}

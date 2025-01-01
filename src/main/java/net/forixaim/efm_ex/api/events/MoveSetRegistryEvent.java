package net.forixaim.efm_ex.api.events;

import com.google.common.collect.Maps;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Map;

public class MoveSetRegistryEvent extends Event implements IModBusEvent
{
    private final Map<CoreCapability, Map<Style, MoveSet>> MoveSetRegistryMap;

    public MoveSetRegistryEvent() {
        MoveSetRegistryMap = Maps.newHashMap();
    }

    public Map<CoreCapability, Map<Style, MoveSet>> getMoveSetRegistryMap() {
        return MoveSetRegistryMap;
    }

    public void register()
    {
        MoveSetRegistryMap.entrySet().forEach((entry ->
                entry.getValue().forEach(
                        (style, moveSet) -> entry.getKey().getAttackSets().put(style, moveSet)
                )));
    }

}

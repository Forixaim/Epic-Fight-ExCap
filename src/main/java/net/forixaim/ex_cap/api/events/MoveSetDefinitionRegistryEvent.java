package net.forixaim.ex_cap.api.events;

import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.Map;

public class MoveSetDefinitionRegistryEvent extends Event implements IModBusEvent
{
    private final Map<String, Runnable> moveSets;

    public MoveSetDefinitionRegistryEvent()
    {
        moveSets = Maps.newHashMap();
    }

    public Map<String, Runnable> getMoveSets() {
        return moveSets;
    }


}

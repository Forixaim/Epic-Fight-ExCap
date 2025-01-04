package net.forixaim.efm_ex.api.events;

import com.google.common.collect.Maps;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.Map;

public class ExCapWeaponRegistryEvent extends Event implements IModBusEvent
{
    private final Map<String, Runnable> ExCapWeapons;

    public ExCapWeaponRegistryEvent()
    {
        ExCapWeapons = Maps.newHashMap();
    }

    public Map<String, Runnable> getExCapWeapons() {
        return ExCapWeapons;
    }
}

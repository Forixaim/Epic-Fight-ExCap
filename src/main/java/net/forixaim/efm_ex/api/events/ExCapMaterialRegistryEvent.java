package net.forixaim.efm_ex.api.events;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.forixaim.efm_ex.api.material.MaterialProperties;
import net.minecraft.world.item.Tier;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.Map;

public class ExCapMaterialRegistryEvent extends Event implements IModBusEvent
{
    private final Map<String, Map<Tier, MaterialProperties>> modMap = Maps.newHashMap();

    public Map<String, Map<Tier, MaterialProperties>> getModMap() {
        return ImmutableMap.copyOf(modMap);
    }

    public void addMaterial(String modID, Tier tier, MaterialProperties properties) {
        modMap.computeIfAbsent(modID, k -> Maps.newHashMap());
        modMap.get(modID).put(tier, properties);
    }
}

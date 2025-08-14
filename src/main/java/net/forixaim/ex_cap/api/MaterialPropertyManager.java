package net.forixaim.ex_cap.api;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.forixaim.ex_cap.api.material.MaterialProperties;
import net.minecraft.world.item.Tier;

import java.util.Map;

public class MaterialPropertyManager
{
    private static final Map<Tier, MaterialProperties> properties = Maps.newHashMap();

    public static Map<Tier, MaterialProperties> getProperties() {
        return ImmutableMap.copyOf(properties);
    }

    static void addEntry(Map.Entry<Tier, MaterialProperties> entry) {
        if (properties.containsKey(entry.getKey())) {
            return;
        }
        properties.put(entry.getKey(), entry.getValue());
    }

    static void addAll(Map<Tier, MaterialProperties> map)
    {
        properties.putAll(map);
    }
}

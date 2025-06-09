package net.forixaim.efm_ex.api;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.forixaim.efm_ex.api.material.MaterialProperties;
import net.minecraft.world.item.Tier;

import java.util.Map;
import java.util.Set;

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

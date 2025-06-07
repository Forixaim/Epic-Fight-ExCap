package net.forixaim.efm_ex.api.material;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.world.item.Tier;

import java.util.Map;

public class MaterialPropertyManager
{
    private static final Map<Tier, MaterialProperties> properties = Maps.newHashMap();

    public static Map<Tier, MaterialProperties> getProperties() {
        return ImmutableMap.copyOf(properties);
    }

    public static void addEntry(Map.Entry<Tier, MaterialProperties> entry) {
        if (properties.containsKey(entry.getKey())) {
            return;
        }
        properties.put(entry.getKey(), entry.getValue());
    }
}

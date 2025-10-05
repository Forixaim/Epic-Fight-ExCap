package net.forixaim.ex_cap.api.utilities;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {

    /**
     * Loads all JSON files under the given base folder for a specific namespace.
     *
     * @param manager    ResourceManager (data pack manager)
     * @param namespace  Mod or pack namespace (e.g. "mymod")
     * @param baseFolder Base folder path, e.g. "capabilities/weapons/abilities"
     * @return ImmutableMap of ResourceLocation → JsonElement
     */
    public static Map<ResourceLocation, JsonElement> getJsonsForNamespace(ResourceManager manager, String namespace, String baseFolder) {
        Map<ResourceLocation, Resource> resources = getResourcesForNamespace(manager, namespace, baseFolder);
        Map<ResourceLocation, JsonElement> result = Maps.newHashMap();

        resources.forEach((key, resource) -> {
            result.put(key, parse(resource));
        });
        return result;
    }

    private static JsonElement parse(Resource resource) {
        try (Reader reader = resource.openAsReader())
        {
            return GsonHelper.parse(reader);
        } catch (IOException e)
        {
            return new JsonObject();
        }
    }

    private static Map<ResourceLocation, Resource> getResourcesForNamespace(ResourceManager manager, String namespace, String baseFolder) {
        // Typical implementation:
        return manager.listResources(baseFolder,
            rl -> rl.getNamespace().equals(namespace) && rl.getPath().endsWith(".json")
        );
    }
}

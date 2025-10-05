package net.forixaim.ex_cap.api.moveset;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.utilities.JsonUtils;
import net.forixaim.ex_cap.capabilities.ExCapWeapon;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExCapWeaponReloadListener extends SimpleJsonResourceReloadListener
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRECTORY = "capabilities/weapons/ex_cap";
    public ExCapWeaponReloadListener() {
        super(GSON, DIRECTORY);
    }

    private void clear()
    {
        ExCapWeapons.REGISTRY.get().forEach( weapon ->
                weapon.getAttackSets().clear());
        Registries.registerMovesets();
    }


    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> json, @NotNull ResourceManager manager, @NotNull ProfilerFiller filler) {
        clear();
        manager.listPacks().forEach(pack ->
                pack.getNamespaces(PackType.SERVER_DATA).forEach(namespace -> {
                    //Lists all weapons
                    ExCapWeapons.REGISTRY.get().forEach(
                            weapon -> {
                                if (Objects.requireNonNull(ExCapWeapons.REGISTRY.get().getKey(weapon)).getNamespace().equals(namespace)) {
                                    Map<ResourceLocation, JsonElement> result = JsonUtils.getJsonsForNamespace(manager, namespace, DIRECTORY + Objects.requireNonNull(ExCapWeapons.REGISTRY.get().getKey(weapon)).getPath());
                                    result.forEach((key, value) -> {
                                        JsonObject gsonObject = value.getAsJsonObject();

                                    });
                                }
                            }
                    );

            }));
    }
}
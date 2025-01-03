package net.forixaim.efm_ex.api.events;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExCapWeaponRegistryEvent extends Event implements IModBusEvent
{
    private final Map<CoreCapability, List<ProviderConditional>> CoreCapabilityConditionalMap;
    private final Map<CoreCapability, Map<Style, MoveSet>> MoveSetRegistryMap;

    public ExCapWeaponRegistryEvent()
    {
        CoreCapabilityConditionalMap = Maps.newHashMap();
        MoveSetRegistryMap = Maps.newHashMap();
    }

    public Map<CoreCapability, Map<Style, MoveSet>> getMoveSetRegistryMap() {
        return MoveSetRegistryMap;
    }

    public Map<CoreCapability, List<ProviderConditional>> getCoreCapabilityConditionalMap() {
        return CoreCapabilityConditionalMap;
    }

    public void addProvider(CoreCapability ExCapWeapon, ProviderConditional... ProviderConditionals)
    {
        CoreCapabilityConditionalMap.computeIfAbsent(ExCapWeapon, (key) -> Lists.newArrayList()).addAll(Arrays.asList(ProviderConditionals));
    }

    public void addMSEZ(CoreCapability ExCapWeapon, Map<Style, MoveSet> MoveSetRegistry)
    {
        if (MoveSetRegistry == null) return;
        MoveSetRegistry.forEach((style, moveSet) -> addMoveset(ExCapWeapon, style, moveSet));
    }

    public void addMoveset(CoreCapability ExCapWeapon, Style Style, MoveSet MoveSet)
    {
        MoveSetRegistryMap.computeIfAbsent(
                ExCapWeapon, (key) -> Maps.newHashMap()
        ).put(Style, MoveSet);
    }

}

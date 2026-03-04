package net.forixaim.ex_cap.api.events;

import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.api.providers.ProviderConditional;
import net.forixaim.ex_cap.capabilities.ExCapWeapon;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExCapMovesetRegistryEvent extends Event implements IModBusEvent
{
    private final Map<DeferredHolder<ExCapWeapon, ExCapWeapon>, List<ProviderConditional>> CoreCapabilityConditionalMap;
    private final Map<DeferredHolder<ExCapWeapon, ExCapWeapon>, Map<Style, MoveSet.MoveSetBuilder>> MoveSetRegistryMap;

    public ExCapMovesetRegistryEvent()
    {
        CoreCapabilityConditionalMap = Maps.newHashMap();
        MoveSetRegistryMap = Maps.newHashMap();
    }

    public Map<DeferredHolder<ExCapWeapon, ExCapWeapon>, Map<Style, MoveSet.MoveSetBuilder>> getMoveSetRegistryMap() {
        return MoveSetRegistryMap;
    }

    public Map<DeferredHolder<ExCapWeapon, ExCapWeapon>, List<ProviderConditional>> getCoreCapabilityConditionalMap() {
        return CoreCapabilityConditionalMap;
    }

    public void addProvider(DeferredHolder<ExCapWeapon, ExCapWeapon> ExCapWeapon, ProviderConditional... ProviderConditionals)
    {
        ExCapWeapon.get().getStyleComboProviderRegistry().addAll(Arrays.asList(ProviderConditionals));
        LogUtils.getLogger().debug(ExCapWeapon.get().toString());
        LogUtils.getLogger().debug(ExCapWeapon.value().getStyleComboProviderRegistry().toString());
    }

    public void addMoveset(DeferredHolder<ExCapWeapon, ExCapWeapon> ExCapWeapon, Style Style, MoveSet.MoveSetBuilder MoveSet)
    {
        ExCapWeapon.get().getAttackSets().put(Style, MoveSet.build());
        LogUtils.getLogger().debug(ExCapWeapon.value().getAttackSets().toString());
    }

}

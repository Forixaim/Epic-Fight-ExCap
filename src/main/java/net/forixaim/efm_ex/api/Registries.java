package net.forixaim.efm_ex.api;

import net.forixaim.efm_ex.api.events.ExCapWeaponRegistryEvent;
import net.minecraftforge.fml.ModLoader;

public class Registries
{
    /**
     * This is to be called after everything has been loaded
     */
    public static void registerAll()
    {
        final ExCapWeaponRegistryEvent registryEvent = new ExCapWeaponRegistryEvent();

        ModLoader.get().postEvent(registryEvent);

        registryEvent.getCoreCapabilityConditionalMap().forEach(
                (coreCapability, conditionals) ->
                        coreCapability.getStyleComboProviderRegistry().addAll(conditionals)
        );
        registryEvent.getMoveSetRegistryMap().forEach(
                (coreCapability, styleMoveSetMap) ->
                        styleMoveSetMap.forEach(
                                (style, moveSet) ->
                                        coreCapability.getAttackSets().put(style, moveSet)
                        )
        );
    }


}

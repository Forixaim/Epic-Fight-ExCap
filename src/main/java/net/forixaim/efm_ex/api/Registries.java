package net.forixaim.efm_ex.api;

import net.forixaim.efm_ex.api.events.ExCapMovesetRegistryEvent;
import net.forixaim.efm_ex.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.efm_ex.api.events.MoveSetDefinitionRegistryEvent;
import net.minecraftforge.fml.ModLoader;

public class Registries
{
    public static void registerMovesets()
    {
        final MoveSetDefinitionRegistryEvent moveSetRegistryEvent = new MoveSetDefinitionRegistryEvent();

        ModLoader.get().postEvent(moveSetRegistryEvent);

        moveSetRegistryEvent.getMoveSets().forEach(
                (moveSet, registryEvent) -> registryEvent.run()
        );
    }

    /**
     * This is to be called after everything has been loaded
     */
    public static void registerCapabilities()
    {
        final ExCapMovesetRegistryEvent registryEvent = new ExCapMovesetRegistryEvent();
        final ExCapWeaponRegistryEvent exCapWeaponRegistryEvent = new ExCapWeaponRegistryEvent();

        ModLoader.get().postEvent(exCapWeaponRegistryEvent);

        exCapWeaponRegistryEvent.getExCapWeapons().forEach(
                (s, runnable) -> runnable.run()
        );

        ModLoader.get().postEvent(registryEvent);

        registryEvent.getCoreCapabilityConditionalMap().forEach(
                (coreCapability, conditionals) ->
                        coreCapability.getStyleComboProviderRegistry().addAll(conditionals)
        );
        registryEvent.getMoveSetRegistryMap().forEach(
                (coreCapability, styleMoveSetMap) ->
                        coreCapability.getAttackSets().putAll(styleMoveSetMap)
        );
    }


}

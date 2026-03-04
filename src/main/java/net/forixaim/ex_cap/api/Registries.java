package net.forixaim.ex_cap.api;

import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;
import net.forixaim.ex_cap.api.events.MoveSetDefinitionRegistryEvent;
import net.neoforged.fml.ModLoader;

public class Registries
{


    public static void registerMaterials()
    {


    }
    /**
     * This is to be called after everything has been loaded
     */
    public static void registerCapabilities()
    {
        MoveSetDefinitionRegistryEvent dynamicEvent = new MoveSetDefinitionRegistryEvent();
        ExCapMovesetRegistryEvent event3 = new ExCapMovesetRegistryEvent();
        ModLoader.postEvent(dynamicEvent);

        dynamicEvent.getMoveSets().forEach((string, runnable) -> runnable.run());

        ModLoader.postEvent(event3);

        event3.getCoreCapabilityConditionalMap().forEach(
                (coreCapability, conditionals) ->
                        coreCapability.getStyleComboProviderRegistry().addAll(conditionals)
        );
        event3.getMoveSetRegistryMap().forEach(
                (coreCapability, styleMoveSetMap) ->
                        styleMoveSetMap.forEach(
                                (style, moveSet) -> coreCapability.getAttackSets().put(style, moveSet.build())
                        )
        );
    }
}

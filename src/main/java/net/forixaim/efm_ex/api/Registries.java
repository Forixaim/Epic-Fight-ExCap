package net.forixaim.efm_ex.api;

import net.forixaim.efm_ex.api.events.ExCapMovesetRegistryEvent;
import net.forixaim.efm_ex.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.efm_ex.api.events.MoveSetDefinitionRegistryEvent;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.minecraftforge.fml.ModLoader;

import javax.swing.text.Style;
import java.util.Map;
import java.util.Objects;

public class Registries
{
    private static ExCapWeaponRegistryEvent event;
    private static MoveSetDefinitionRegistryEvent event2;
    private static ExCapMovesetRegistryEvent event3;
    public static void registerMovesets()
    {
        event2 = new MoveSetDefinitionRegistryEvent();

        ModLoader.get().postEvent(event2);

        event2.getMoveSets().forEach(
                (moveSet, registryEvent) -> registryEvent.run()
        );
    }

    /**
     * This is to be called after everything has been loaded
     */
    public static void registerCapabilities()
    {
        event3 = new ExCapMovesetRegistryEvent();
        event = new ExCapWeaponRegistryEvent();

        ModLoader.get().postEvent(event);

        event.getExCapWeapons().forEach(
                (s, runnable) -> runnable.run()
        );

        ModLoader.get().postEvent(event3);

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

    private static void validate(Map<Style, MoveSet> setMap)
    {
        //Validate Animations
        setMap.forEach((style, moveSet) -> moveSet.getAutoAttackAnimations().forEach(
                animation -> Objects.requireNonNull(animation, "Animation must not be null")
        ));
    }

}

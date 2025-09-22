package net.forixaim.ex_cap.api;

import net.forixaim.ex_cap.api.events.ExCapMaterialRegistryEvent;
import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;
import net.forixaim.ex_cap.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.ex_cap.api.events.MoveSetDefinitionRegistryEvent;
import net.forixaim.ex_cap.api.material.MaterialProperties;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.fml.ModLoader;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.Map;

public class Registries
{
    public static void registerMovesets()
    {
        MoveSetDefinitionRegistryEvent event2 = new MoveSetDefinitionRegistryEvent();

        ModLoader.get().postEvent(event2);

        event2.getMoveSets().forEach(
                (moveSet, registryEvent) -> registryEvent.run()
        );
    }

    public static MaterialProperties quickRegister(float pierce, float impact, float maxStrikes)
    {
        return new MaterialProperties(Map.ofEntries(
                Map.entry(EpicFightAttributes.ARMOR_NEGATION.get(), (double)pierce),
                Map.entry(EpicFightAttributes.IMPACT.get(), (double)impact),
                Map.entry(EpicFightAttributes.MAX_STRIKES.get(), (double)maxStrikes)
        ));
    }

    public static void registerMaterials()
    {
        Map<Tier, MaterialProperties> properties = Map.ofEntries(
                Map.entry(Tiers.WOOD, quickRegister(0f, 1f, 1)),
                Map.entry(Tiers.STONE, quickRegister(0f, 2f, 1)),
                Map.entry(Tiers.IRON, quickRegister(5f, 3f, 1)),
                Map.entry(Tiers.GOLD, quickRegister(0f, 2f, 2)),
                Map.entry(Tiers.DIAMOND, quickRegister(7f, 3f, 2)),
                Map.entry(Tiers.NETHERITE, quickRegister(10f, 3f, 3))
        );

        MaterialPropertyManager.addAll(properties);

        ExCapMaterialRegistryEvent event = new ExCapMaterialRegistryEvent();
        ModLoader.get().postEvent(event);
        event.getModMap().values().forEach(MaterialPropertyManager::addAll);
    }
    /**
     * This is to be called after everything has been loaded
     */
    public static void registerCapabilities()
    {
        ExCapMovesetRegistryEvent event3 = new ExCapMovesetRegistryEvent();
        ExCapWeaponRegistryEvent event = new ExCapWeaponRegistryEvent();

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
}

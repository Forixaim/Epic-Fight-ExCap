package net.forixaim.ex_cap.capabilities.weapon_presets;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.forixaim.ex_cap.capabilities.CoreCapability;
import net.forixaim.ex_cap.capabilities.ExCapCategories;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXGloveCapability;
import net.forixaim.ex_cap.capabilities.weaponcaps.compat.EXSpellCapability;
import net.forixaim.ex_cap.registry.ItemRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.EpicFightItems;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExCapWeapons
{
    public static final ResourceKey<Registry<CoreCapability>> REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "weapons"));

    public static CoreCapability BOKKEN = null;
    public static CoreCapability SWORD;
    public static CoreCapability SHIELD;
    public static CoreCapability AXE;
    public static CoreCapability LONGSWORD;
    public static CoreCapability BOW;
    public static CoreCapability GREATSWORD;
    public static CoreCapability GLOVE;
    public static CoreCapability SPEAR;
    public static CoreCapability SPELL;
    public static CoreCapability TACHI;
    public static CoreCapability UCHIGATANA;
    public static CoreCapability DAGGER;

    @SubscribeEvent
    public static void onRegister(ExCapWeaponRegistryEvent event)
    {
        event.getExCapWeapons().put(EpicFightEXCapability.MODID, ExCapWeapons::build);
    }

    public static void build()
    {
        GLOVE = CoreCapability.quickStart(
                builder -> builder
                        .constructor(EXGloveCapability::new)
                        .category(CapabilityItem.WeaponCategories.FIST)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get()),
                1, 1, 1
        );

        SHIELD = CoreCapability.quickStart(
                builder -> builder
                        .category(CapabilityItem.WeaponCategories.SHIELD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .collider(ColliderPreset.FIST)
                , 1, 1, 1
        );


        DAGGER = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.DAGGER)
                        .collider(ColliderPreset.DAGGER)
                        .swingSound(EpicFightSounds.WHOOSH_SMALL.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                ,
                1, 1, 1
        );

        BOKKEN = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                        .collider(ColliderPreset.SWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                ,
                1, 1, 1
        );

        SWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                        .collider(ColliderPreset.SWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
                ,
                1, 1, 1
        );

        LONGSWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.LONGSWORD)
                        .collider(ColliderPreset.LONGSWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
                ,
                1, 1, 1
        );
        CoreCapability.addSheath(EpicFightItems.IRON_LONGSWORD.get(), ItemRegistry.IRON_LONGSWORD_SHEATH.get());
        CoreCapability.addSheath(EpicFightItems.GOLDEN_LONGSWORD.get(), ItemRegistry.IRON_LONGSWORD_SHEATH.get());

        GREATSWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.GREATSWORD)
                        .collider(ColliderPreset.GREATSWORD)
                        .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        AXE = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.AXE)
                        .collider(ColliderPreset.TOOLS)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        BOW = CoreCapability.quickStart(
                builder -> builder.category(ExCapCategories.BOW)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .zoomInType(CapabilityItem.ZoomInType.USE_TICK),
                1, 1, 1
        );

        SPEAR = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SPEAR)
                        .collider(ColliderPreset.SPEAR)
                        .swingSound(EpicFightSounds.WHOOSH_ROD.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        TACHI = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.TACHI)
                        .collider(ColliderPreset.TACHI)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        UCHIGATANA = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.UCHIGATANA)
                        .collider(ColliderPreset.UCHIGATANA)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 0.7f, 1
        );

        SPELL = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.RANGED)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .constructor(EXSpellCapability::new),
                1, 1, 1
        );
    }
}

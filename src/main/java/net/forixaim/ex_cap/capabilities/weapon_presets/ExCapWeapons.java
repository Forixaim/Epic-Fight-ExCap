package net.forixaim.ex_cap.capabilities.weapon_presets;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.capabilities.ExCapWeapon;
import net.forixaim.ex_cap.capabilities.ExCapCategories;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXGloveCapability;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class ExCapWeapons
{
    public static final ResourceKey<Registry<ExCapWeapon>> REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "weapons"));

    public static final DeferredRegister<ExCapWeapon> EX_CAP_WEAPONS = DeferredRegister.create(REGISTRY_KEY, EpicFightEXCapability.MODID);
    public static final Registry<ExCapWeapon> REGISTRY = EX_CAP_WEAPONS.makeRegistry(builder -> builder.sync(true));


    public static DeferredHolder<ExCapWeapon, ExCapWeapon> BOKKEN = EX_CAP_WEAPONS.register(
            "bokken", () -> ExCapWeapon.quickStart(
                    builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                            .collider(ColliderPreset.SWORD)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    ,
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> SWORD = EX_CAP_WEAPONS.register("sword",
            () -> ExCapWeapon.quickStart(
                    builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                            .collider(ColliderPreset.SWORD)
                            .swingSound(EpicFightSounds.WHOOSH_ROD.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get())
                    ,
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> SHIELD = EX_CAP_WEAPONS.register("shield",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.SHIELD)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get())
                            .collider(ColliderPreset.FIST),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> AXE = EX_CAP_WEAPONS.register("axe",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.AXE)
                            .collider(ColliderPreset.TOOLS)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> LONGSWORD = EX_CAP_WEAPONS.register("longsword",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.LONGSWORD)
                            .collider(ColliderPreset.LONGSWORD)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> BOW = EX_CAP_WEAPONS.register("bow",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(ExCapCategories.BOW)
                            .collider(ColliderPreset.FIST)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get())
                            .zoomInType(CapabilityItem.ZoomInType.USE_TICK),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> GREATSWORD = EX_CAP_WEAPONS.register("greatsword",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.GREATSWORD)
                            .collider(ColliderPreset.GREATSWORD)
                            .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> GLOVE = EX_CAP_WEAPONS.register("glove",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .constructor(EXGloveCapability::new)
                            .category(CapabilityItem.WeaponCategories.FIST)
                            .collider(ColliderPreset.FIST)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> SPEAR = EX_CAP_WEAPONS.register("spear",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.SPEAR)
                            .collider(ColliderPreset.SPEAR)
                            .swingSound(EpicFightSounds.WHOOSH_ROD.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> TACHI = EX_CAP_WEAPONS.register("tachi",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.TACHI)
                            .collider(ColliderPreset.TACHI)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 1, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> UCHIGATANA = EX_CAP_WEAPONS.register("uchigatana",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.UCHIGATANA)
                            .collider(ColliderPreset.UCHIGATANA)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get()),
                    1, 0.7f, 1
            ));

    public static final DeferredHolder<ExCapWeapon, ExCapWeapon> DAGGER = EX_CAP_WEAPONS.register("dagger",
            () -> ExCapWeapon.quickStart(
                    builder -> builder
                            .category(CapabilityItem.WeaponCategories.DAGGER)
                            .collider(ColliderPreset.DAGGER)
                            .swingSound(EpicFightSounds.WHOOSH_SMALL.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get()),
                    1, 1, 1
            ));
}

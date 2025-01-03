package net.forixaim.efm_ex.capabilities.weapon_presets;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXBowWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.compat.EXSpellCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class ExCapWeapons
{
    public static CoreCapability BOKKEN;
    public static CoreCapability SWORD;
    public static CoreCapability AXE;
    public static CoreCapability LONGSWORD;
    public static CoreCapability BOW;
    public static CoreCapability GREATSWORD;
    public static CoreCapability SPEAR;
    public static CoreCapability SPELL;
    public static CoreCapability TACHI;
    public static CoreCapability UCHIGATANA;
    public static CoreCapability DAGGER;

    static
    {
        DAGGER = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.DAGGER)
                        .collider(ColliderPreset.DAGGER)
                        .swingSound(EpicFightSounds.WHOOSH_SMALL.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
        );

        BOKKEN = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                        .collider(ColliderPreset.SWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
        );

        SWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                        .collider(ColliderPreset.SWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        LONGSWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.LONGSWORD)
                        .collider(ColliderPreset.LONGSWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        GREATSWORD = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.GREATSWORD)
                        .collider(ColliderPreset.GREATSWORD)
                        .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        AXE = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.AXE)
                        .collider(ColliderPreset.TOOLS)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        BOW = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.RANGED)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .constructor(EXBowWeaponCapability::new)
        );

        SPEAR = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SPEAR)
                        .collider(ColliderPreset.SPEAR)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        TACHI = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.TACHI)
                        .collider(ColliderPreset.TACHI)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        UCHIGATANA = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.UCHIGATANA)
                        .collider(ColliderPreset.UCHIGATANA)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
        );

        SPELL = CoreCapability.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.RANGED)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .constructor(EXSpellCapability::new)
        );
    }
}

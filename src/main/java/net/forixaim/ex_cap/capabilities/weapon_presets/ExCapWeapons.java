package net.forixaim.ex_cap.capabilities.weapon_presets;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.ex_cap.capabilities.ExCapWeapon;
import net.forixaim.ex_cap.capabilities.ExCapCategories;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXGloveCapability;
import net.forixaim.ex_cap.capabilities.weaponcaps.compat.EXSpellCapability;
import net.forixaim.ex_cap.registry.ItemRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.item.EpicFightItems;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExCapWeapons
{
    public static final ResourceKey<Registry<ExCapWeapon>> REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "weapons"));

    public static final DeferredRegister<ExCapWeapon> EX_CAP_WEAPONS = DeferredRegister.create(REGISTRY_KEY, EpicFightEXCapability.MODID);

    public static final Supplier<IForgeRegistry<ExCapWeapon>> REGISTRY = EX_CAP_WEAPONS.makeRegistry(() -> new RegistryBuilder<ExCapWeapon>().setName(REGISTRY_KEY.location()));


    public static RegistryObject<ExCapWeapon> BOKKEN = EX_CAP_WEAPONS.register(
            "bokken", () -> ExCapWeapon.quickStart(
                    builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                            .collider(ColliderPreset.SWORD)
                            .swingSound(EpicFightSounds.WHOOSH.get())
                            .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    ,
                    1, 1, 1
            ));

    public static final RegistryObject<ExCapWeapon> SWORD = EX_CAP_WEAPONS.register("sword",
            () -> ExCapWeapon.quickStart(
                    builder -> builder.category(CapabilityItem.WeaponCategories.SWORD)
                            .collider(ColliderPreset.SWORD)
                            .swingSound(EpicFightSounds.WHOOSH_ROD.get())
                            .hitSound(EpicFightSounds.BLADE_HIT.get())
                    ,
                    1, 1, 1
            ));

    public static ExCapWeapon SHIELD;
    public static ExCapWeapon AXE;
    public static ExCapWeapon LONGSWORD;
    public static ExCapWeapon BOW;
    public static ExCapWeapon GREATSWORD;
    public static ExCapWeapon GLOVE;
    public static ExCapWeapon SPEAR;
    public static ExCapWeapon SPELL;
    public static ExCapWeapon TACHI;
    public static ExCapWeapon UCHIGATANA;
    public static ExCapWeapon DAGGER;

    @SubscribeEvent
    public static void onRegister(ExCapWeaponRegistryEvent event)
    {
        event.getExCapWeapons().put(EpicFightEXCapability.MODID, ExCapWeapons::build);
    }

    public static void build()
    {
        GLOVE = ExCapWeapon.quickStart(
                builder -> builder
                        .constructor(EXGloveCapability::new)
                        .category(CapabilityItem.WeaponCategories.FIST)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get()),
                1, 1, 1
        );

        SHIELD = ExCapWeapon.quickStart(
                builder -> builder
                        .category(CapabilityItem.WeaponCategories.SHIELD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .collider(ColliderPreset.FIST)
                , 1, 1, 1
        );


        DAGGER = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.DAGGER)
                        .collider(ColliderPreset.DAGGER)
                        .swingSound(EpicFightSounds.WHOOSH_SMALL.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                ,
                1, 1, 1
        );

        LONGSWORD = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.LONGSWORD)
                        .collider(ColliderPreset.LONGSWORD)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get())
                ,
                1, 1, 1
        );
        ExCapWeapon.addSheath(EpicFightItems.IRON_LONGSWORD.get(), ItemRegistry.IRON_LONGSWORD_SHEATH.get());
        ExCapWeapon.addSheath(EpicFightItems.GOLDEN_LONGSWORD.get(), ItemRegistry.IRON_LONGSWORD_SHEATH.get());

        GREATSWORD = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.GREATSWORD)
                        .collider(ColliderPreset.GREATSWORD)
                        .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        AXE = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.AXE)
                        .collider(ColliderPreset.TOOLS)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        BOW = ExCapWeapon.quickStart(
                builder -> builder.category(ExCapCategories.BOW)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .zoomInType(CapabilityItem.ZoomInType.USE_TICK),
                1, 1, 1
        );

        SPEAR = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.SPEAR)
                        .collider(ColliderPreset.SPEAR)
                        .swingSound(EpicFightSounds.WHOOSH_ROD.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        TACHI = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.TACHI)
                        .collider(ColliderPreset.TACHI)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 1, 1
        );

        UCHIGATANA = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.UCHIGATANA)
                        .collider(ColliderPreset.UCHIGATANA)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLADE_HIT.get()),
                1, 0.7f, 1
        );

        SPELL = ExCapWeapon.quickStart(
                builder -> builder.category(CapabilityItem.WeaponCategories.RANGED)
                        .collider(ColliderPreset.FIST)
                        .swingSound(EpicFightSounds.WHOOSH.get())
                        .hitSound(EpicFightSounds.BLUNT_HIT.get())
                        .constructor(EXSpellCapability::new),
                1, 1, 1
        );
    }
}

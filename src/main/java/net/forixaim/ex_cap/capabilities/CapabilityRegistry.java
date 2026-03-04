package net.forixaim.ex_cap.capabilities;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.MaterialPropertyManager;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.material.MaterialProperties;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import yesman.epicfight.api.event.types.registry.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.utils.math.ValueModifier;

import yesman.epicfight.registry.entries.EpicFightParticles;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.Map;
import java.util.function.Function;

public class CapabilityRegistry
{
    public static Function<Item, CapabilityItem.Builder<?>> process(Holder<ExCapWeapon> weapon)
    {
        Function<Item, CapabilityItem.Builder<?>> method = item -> processWeapon(item, weapon);

        return method;
    }

    private static WeaponCapability.Builder processWeapon(Item item, Holder<ExCapWeapon> weapon) {
        WeaponCapability.Builder builder0 = weapon.value().export();
        LogUtils.getLogger().debug(weapon.value().toString());

        LogUtils.getLogger().debug("Processing one weapon");
        if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
            builder.hitSound(tieredItem.getTier() == Tiers.WOOD
                    ? EpicFightSounds.BLUNT_HIT.get()
                    : EpicFightSounds.BLADE_HIT.get());

            builder.hitParticle(tieredItem.getTier() == Tiers.WOOD
                    ? EpicFightParticles.HIT_BLUNT.get()
                    : EpicFightParticles.HIT_BLADE.get());
        }

        return builder0;
    }
}

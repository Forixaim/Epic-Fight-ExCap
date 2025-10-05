package net.forixaim.ex_cap.capabilities;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.MaterialPropertyManager;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.material.MaterialProperties;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.Map;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry
{
    private static Function<Item, CapabilityItem.Builder> process(ExCapWeapon weapon)
    {
        return item ->
        {
            CapabilityItem.Builder builder0;

            builder0 = weapon.export();

            Map<Attribute, ValueModifier> attributeModifier = weapon.getAttModifiers();


            if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
                if (MaterialPropertyManager.getProperties().containsKey(tieredItem.getTier()))
                {
                    MaterialProperties properties = MaterialPropertyManager.getProperties().get(tieredItem.getTier());
                    attributeModifier.forEach((attribute, modifier) -> {
                        double finalValue = ValueModifier.calculator().attach(modifier).getResult(properties.attributeModifier().get(attribute).floatValue());
                        builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
                    });
                }
                else
                {
                    MaterialProperties properties = Registries.quickRegister(1, 1, 1);
                    attributeModifier.forEach((attribute, modifier) -> {
                        double finalValue = ValueModifier.calculator().attach(modifier).getResult(properties.attributeModifier().get(attribute).floatValue());
                        builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
                    });
                }
                builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
                builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
            }

            return builder0;
        };
    }

	@SubscribeEvent
	public static void Register(WeaponCapabilityPresetRegistryEvent event)
	{
        ExCapWeapons.REGISTRY.get().forEach(item -> event.getTypeEntry().put(ExCapWeapons.REGISTRY.get().getKey(item), process(item)));
	}
}

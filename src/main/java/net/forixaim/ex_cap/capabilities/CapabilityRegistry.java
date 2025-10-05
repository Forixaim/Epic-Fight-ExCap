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
	public static final Function<Item, CapabilityItem.Builder> SHIELD = item ->
	{
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.SHIELD.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.SHIELD.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.SHIELD.getAttModifiers();

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
		}

		return builder0;
	};

	public static final Function<Item, CapabilityItem.Builder> AXE = item ->
	{
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.AXE.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.AXE.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.AXE.getAttModifiers();

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


	public static final Function<Item, CapabilityItem.Builder> LONGSWORD = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.LONGSWORD.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.LONGSWORD.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.LONGSWORD.getAttModifiers();


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

	public static final Function<Item, CapabilityItem.Builder> GREATSWORD = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.GREATSWORD.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.GREATSWORD.export(true);
		}
		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.GREATSWORD.getAttModifiers();

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

	public static final Function<Item, CapabilityItem.Builder> TACHI = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.TACHI.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.TACHI.export(true);
		}
		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.TACHI.getAttModifiers();

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

	public static final Function<Item, CapabilityItem.Builder> SPEAR = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.SPEAR.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.SPEAR.export(true);
		}
		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.SPEAR.getAttModifiers();

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

	public static final Function<Item, CapabilityItem.Builder> FIST = item ->
	{
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.GLOVE.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 =  ExCapWeapons.GLOVE.export(true);
		}
		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.GLOVE.getAttModifiers();

		if (item instanceof TieredItem tieredItem)
		{
			if (MaterialPropertyManager.getProperties().containsKey(tieredItem.getTier()))
			{
				MaterialProperties properties = MaterialPropertyManager.getProperties().get(tieredItem.getTier());
				CapabilityItem.Builder finalBuilder = builder0;
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = ValueModifier.calculator().attach(modifier).getResult(properties.attributeModifier().get(attribute).floatValue());
					finalBuilder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				CapabilityItem.Builder finalBuilder1 = builder0;
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = ValueModifier.calculator().attach(modifier).getResult(properties.attributeModifier().get(attribute).floatValue());
					finalBuilder1.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
		}
		return builder0;
	};

	public static final Function<Item, CapabilityItem.Builder> DAGGER = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.DAGGER.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.DAGGER.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.DAGGER.getAttModifiers();


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

	public static final Function<Item, CapabilityItem.Builder> UCHIGATANA = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.UCHIGATANA.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.UCHIGATANA.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.UCHIGATANA.getAttModifiers();


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

	public static final Function<Item, CapabilityItem.Builder> SPELL = item -> {
		try
		{
			return ExCapWeapons.SPELL.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			return ExCapWeapons.SPELL.export(true);
		}
	};

	public static final Function<Item, CapabilityItem.Builder> BOW = item -> {
		CapabilityItem.Builder builder0;

		LogUtils.getLogger().debug("item : {} with Bow Capability", item.getDescriptionId());

		builder0 = ExCapWeapons.BOW.export();

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.BOW.getAttModifiers();


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
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "longsword"), LONGSWORD);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "greatsword"), GREATSWORD);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "tachi"), TACHI);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "dagger"), DAGGER);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "spear"), SPEAR);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "spell"), SPELL);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "bow"), BOW);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "shield"), SHIELD);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "axe"), AXE);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "uchigatana"), UCHIGATANA);
		event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "fist"), FIST);

	}
}

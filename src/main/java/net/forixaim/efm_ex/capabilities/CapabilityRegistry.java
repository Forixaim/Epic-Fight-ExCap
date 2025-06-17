package net.forixaim.efm_ex.capabilities;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.api.MaterialPropertyManager;
import net.forixaim.efm_ex.api.Registries;
import net.forixaim.efm_ex.api.material.MaterialProperties;
import net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.data.reloader.ItemCapabilityReloadListener;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.Map;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry
{
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};
	public static final Function<Item, CapabilityItem.Builder> BOKKEN = item ->
	{
		CapabilityItem.Builder builder0;

		CoreCapability core = ExCapWeapons.BOKKEN;

		try
		{
			builder0 = core.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = core.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = core.getAttModifiers();


		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
			if (MaterialPropertyManager.getProperties().containsKey(tieredItem.getTier()))
			{
				MaterialProperties properties = MaterialPropertyManager.getProperties().get(tieredItem.getTier());
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};
	public static final Function<Item, CapabilityItem.Builder> SWORD = item -> {
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.SWORD.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.SWORD.export(true);
		}

		Map<Attribute, ValueModifier> attributeModifier = ExCapWeapons.SWORD.getAttModifiers();


		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
			if (MaterialPropertyManager.getProperties().containsKey(tieredItem.getTier()))
			{
				MaterialProperties properties = MaterialPropertyManager.getProperties().get(tieredItem.getTier());
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};

	public static final Function<Item, CapabilityItem.Builder> GREATSWORD = item -> {
		CapabilityItem.Builder builder0 = ExCapWeapons.GREATSWORD.export();

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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					finalBuilder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				CapabilityItem.Builder finalBuilder1 = builder0;
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
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
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			else
			{
				MaterialProperties properties = Registries.quickRegister(1, 1, 1);
				attributeModifier.forEach((attribute, modifier) -> {
					double finalValue = modifier.getTotalValue(properties.attributeModifier().get(attribute).floatValue());
					builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(attribute, new AttributeModifier("ex_cap_attribute", finalValue, AttributeModifier.Operation.ADDITION)));
				});
			}
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};

	@SubscribeEvent
	public static void Register(WeaponCapabilityPresetRegistryEvent Event)
	{
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "bokken"), BOKKEN);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "sword"), SWORD);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "longsword"), LONGSWORD);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "greatsword"), GREATSWORD);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "tachi"), TACHI);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "dagger"), DAGGER);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "spear"), SPEAR);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "spell"), SPELL);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "bow"), BOW);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "axe"), AXE);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "uchigatana"), UCHIGATANA);
		Event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(EpicFightEXCapability.MODID, "fist"), FIST);

	}
}

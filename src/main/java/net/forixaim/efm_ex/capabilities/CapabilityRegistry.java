package net.forixaim.efm_ex.capabilities;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.data.reloader.ItemCapabilityReloadListener;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};
	public static final Function<Item, CapabilityItem.Builder> BOKKEN = item ->
	{
		CapabilityItem.Builder builder0;

		try
		{
			builder0 = ExCapWeapons.BOKKEN.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			builder0 = ExCapWeapons.BOKKEN.export(true);
		}

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
			builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
			builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
		}

		return builder0;
	};

	public static final Function<Item, CapabilityItem.Builder> FIST = item ->
	{
		try
		{
			return ExCapWeapons.GLOVE.export();
		}
		catch (NoSuchMethodError e)
		{
			LogUtils.getLogger().warn(e.getMessage());
			return ExCapWeapons.GLOVE.export(true);
		}
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

		if (item instanceof TieredItem tieredItem && builder0 instanceof WeaponCapability.Builder builder) {
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

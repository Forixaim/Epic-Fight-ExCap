package net.forixaim.efm_ex.capabilities;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry
{
	public static final Function<Item, CapabilityItem.Builder> BOKKEN = (item ->
			BokkenType.getInstance().export());

	public static final Function<Item, CapabilityItem.Builder> SWORD = (item ->
			SwordType.getInstance().export());

	public static final Function<Item, CapabilityItem.Builder> LONGSWORD = (item ->
			LongswordType.getInstance().export());
	public static final Function<Item, CapabilityItem.Builder> GREATSWORD = (item ->
			GreatswordType.getInstance().export());
	public static final Function<Item, CapabilityItem.Builder> TACHI = (item ->
			TachiType.getInstance().export());
	public static final Function<Item, CapabilityItem.Builder> SPEAR = (item ->
			SpearType.getInstance().export());
	public static final Function<Item, CapabilityItem.Builder> DAGGER = (item ->
			DaggerType.getInstance().export());

	public static final Function<Item, CapabilityItem.Builder> SPELL= (item ->
			MNASpellType.getInstance().export());

	@SubscribeEvent
	public static void Register(WeaponCapabilityPresetRegistryEvent Event)
	{
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "bokken"), BOKKEN);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "sword"), SWORD);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "longsword"), LONGSWORD);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "greatsword"), GREATSWORD);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "tachi"), TACHI);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "dagger"), DAGGER);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "spear"), SPEAR);

	}
}

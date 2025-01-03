package net.forixaim.efm_ex.capabilities;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons;
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
	public static final Function<Item, CapabilityItem.Builder> AXE = item -> ExCapWeapons.AXE.export();
	public static final Function<Item, CapabilityItem.Builder> BOKKEN = item -> ExCapWeapons.BOKKEN.export();
	public static final Function<Item, CapabilityItem.Builder> SWORD = item -> ExCapWeapons.SWORD.export();
	public static final Function<Item, CapabilityItem.Builder> LONGSWORD = item -> ExCapWeapons.LONGSWORD.export();
	public static final Function<Item, CapabilityItem.Builder> GREATSWORD = item -> ExCapWeapons.GREATSWORD.export();
	public static final Function<Item, CapabilityItem.Builder> TACHI = item -> ExCapWeapons.TACHI.export();
	public static final Function<Item, CapabilityItem.Builder> SPEAR = item -> ExCapWeapons.SPEAR.export();
	public static final Function<Item, CapabilityItem.Builder> DAGGER = item -> ExCapWeapons.DAGGER.export();
	public static final Function<Item, CapabilityItem.Builder> UCHIGATANA = item -> ExCapWeapons.UCHIGATANA.export();
	public static final Function<Item, CapabilityItem.Builder> SPELL= item -> ExCapWeapons.SPELL.export();
	public static final Function<Item, CapabilityItem.Builder> BOW = item -> ExCapWeapons.BOW.export();

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
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "spell"), SPELL);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "bow"), BOW);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "axe"), AXE);
		Event.getTypeEntry().put(new ResourceLocation(EpicFightEXCapability.MODID, "uchigatana"), UCHIGATANA);

	}
}

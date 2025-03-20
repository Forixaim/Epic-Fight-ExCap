package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.api.Registries;
import net.forixaim.efm_ex.api.events.ExCapMovesetRegistryEvent;

import net.forixaim.efm_ex.capabilities.weapon_presets.MovesetMappings;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.data.reloader.ItemCapabilityReloadListener;

import static net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons.*;
import static net.forixaim.efm_ex.capabilities.weapon_presets.MainConditionals.*;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponModificationRegistry
{
	@SubscribeEvent
	public static void registerExCap(ExCapMovesetRegistryEvent event)
	{

		event.addProvider(BOKKEN, DualSwords, default1HWieldStyle);
		event.addProvider(LONGSWORD, SwordShieldLS, default2HWieldStyle, LiechtenauerCondition);
		event.addProvider(SWORD, DualSwords, default1HWieldStyle);
		event.addProvider(UCHIGATANA, UchigatanaSheathed, default2HWieldStyle);
		event.addProvider(GREATSWORD, default2HWieldStyle);
		event.addProvider(TACHI, default2HWieldStyle);
		event.addProvider(BOW, default2HWieldStyle);
		event.addProvider(AXE, default1HWieldStyle);
		event.addProvider(SPELL, default1HWieldStyle);
		event.addProvider(SPEAR, default2HWieldStyle, SwordShieldLS);
		event.addProvider(DAGGER, default1HWieldStyle);
		event.addProvider(GLOVE, default1HWieldStyle);

		MovesetMappings.addMovesets(event);
	}
}

package net.forixaim.ex_cap.capabilities;


import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;

import net.forixaim.ex_cap.capabilities.weapon_presets.MovesetMappings;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons.*;
import static net.forixaim.ex_cap.capabilities.weapon_presets.MainConditionals.*;

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
		event.addProvider(BOW, defaultRanged);
		event.addProvider(AXE, default1HWieldStyle);
		event.addProvider(SPELL, default1HWieldStyle);
		event.addProvider(SPEAR, default2HWieldStyle, SwordShieldLS);
		event.addProvider(DAGGER, DualDaggers, default1HWieldStyle);
		event.addProvider(GLOVE, default1HWieldStyle);
		MovesetMappings.addMovesets(event);
	}
}

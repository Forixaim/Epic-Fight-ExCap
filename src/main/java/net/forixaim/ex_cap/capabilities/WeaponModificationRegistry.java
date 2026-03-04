package net.forixaim.ex_cap.capabilities;


import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;

import net.forixaim.ex_cap.capabilities.weapon_presets.MovesetMappings;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import static net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons.*;
import static net.forixaim.ex_cap.capabilities.weapon_presets.MainConditionals.*;

@EventBusSubscriber(modid = EpicFightEXCapability.MODID)
public class WeaponModificationRegistry
{
	@SubscribeEvent
	public static void registerExCap(ExCapMovesetRegistryEvent event)
	{
		event.addProvider(BOKKEN.value(), DualSwords, default1HWieldStyle);
		event.addProvider(LONGSWORD.value(), SwordShieldLS, default2HWieldStyle, LiechtenauerCondition);
		event.addProvider(SWORD.value(), DualSwords, default1HWieldStyle);
		event.addProvider(UCHIGATANA.value(), UchigatanaSheathed, default2HWieldStyle);
		event.addProvider(GREATSWORD.value(), default2HWieldStyle);
		event.addProvider(TACHI.value(), default2HWieldStyle);
		event.addProvider(BOW.value(), defaultRanged);
		event.addProvider(AXE.value(), default1HWieldStyle);
		event.addProvider(SPEAR.value(), default2HWieldStyle, SwordShieldLS);
		event.addProvider(DAGGER.value(), DualDaggers, default1HWieldStyle);
		event.addProvider(GLOVE.value(), default1HWieldStyle);
		MovesetMappings.addMovesets(event);
	}
}

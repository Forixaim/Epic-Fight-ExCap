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
		event.addProvider(BOKKEN.get(), DualSwords, default1HWieldStyle);
		event.addProvider(LONGSWORD.get(), SwordShieldLS, default2HWieldStyle, LiechtenauerCondition);
		event.addProvider(SWORD.get(), DualSwords, default1HWieldStyle);
		event.addProvider(UCHIGATANA.get(), UchigatanaSheathed, default2HWieldStyle);
		event.addProvider(GREATSWORD.get(), default2HWieldStyle);
		event.addProvider(TACHI.get(), default2HWieldStyle);
		event.addProvider(BOW.get(), defaultRanged);
		event.addProvider(AXE.get(), default1HWieldStyle);
		event.addProvider(SPELL.get(), default1HWieldStyle);
		event.addProvider(SPEAR.get(), default2HWieldStyle, SwordShieldLS);
		event.addProvider(DAGGER.get(), DualDaggers, default1HWieldStyle);
		event.addProvider(GLOVE.get(), default1HWieldStyle);
		MovesetMappings.addMovesets(event);
	}
}

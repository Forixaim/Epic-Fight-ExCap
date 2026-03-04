package net.forixaim.ex_cap.capabilities;


import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.api.events.ExCapMovesetRegistryEvent;

import net.forixaim.ex_cap.capabilities.weapon_presets.MovesetMappings;

import static net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons.*;
import static net.forixaim.ex_cap.capabilities.weapon_presets.MainConditionals.*;

public class WeaponModificationRegistry
{

	public static void registerExCap(ExCapMovesetRegistryEvent event)
	{
        LogUtils.getLogger().debug("Registering ExCap Moveset Registry Event");
		event.addProvider(BOKKEN, DualSwords, default1HWieldStyle);
		event.addProvider(LONGSWORD, SwordShieldLS, default2HWieldStyle, LiechtenauerCondition);
		event.addProvider(SWORD, DualSwords, default1HWieldStyle);
		event.addProvider(UCHIGATANA, UchigatanaSheathed, default2HWieldStyle);
		event.addProvider(GREATSWORD, default2HWieldStyle);
		event.addProvider(TACHI, default2HWieldStyle);
		event.addProvider(BOW, defaultRanged);
		event.addProvider(AXE, default1HWieldStyle);
		event.addProvider(SPEAR, default2HWieldStyle, SwordShieldLS);
		event.addProvider(DAGGER, DualDaggers, default1HWieldStyle);
		event.addProvider(GLOVE, default1HWieldStyle);
        MovesetMappings.addMovesets(event);
	}
}

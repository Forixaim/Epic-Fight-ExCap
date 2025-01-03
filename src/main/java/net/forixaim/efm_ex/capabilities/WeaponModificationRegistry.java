package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.api.events.ExCapWeaponRegistryEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons.*;
import static net.forixaim.efm_ex.capabilities.weapon_presets.MovesetMappings.*;
import static net.forixaim.efm_ex.capabilities.weapon_presets.MainConditionals.*;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponModificationRegistry
{
	@SubscribeEvent
	public static void registerExCap(ExCapWeaponRegistryEvent event)
	{
		event.addProvider(BOKKEN, DualSwords, default1HWieldStyle);
		event.addMSEZ(BOKKEN, bokkenMovesets);

		event.addProvider(LONGSWORD, SwordShieldLS, default2HWieldStyle, LiechtenauerCondition);
		event.addMSEZ(LONGSWORD, longswordMovesets);

		event.addProvider(SWORD, DualDaggers, default1HWieldStyle);
		event.addMSEZ(SWORD, swordMovesets);

		event.addProvider(UCHIGATANA, UchigatanaSheathed, default2HWieldStyle);
		event.addMSEZ(UCHIGATANA, uchigatanaMovesets);

		event.addProvider(GREATSWORD, default2HWieldStyle);
		event.addMSEZ(GREATSWORD, greatswordMovesets);

		event.addProvider(TACHI, default2HWieldStyle);
		event.addMSEZ(TACHI, tachiMovesets);

		event.addProvider(BOW, default2HWieldStyle);
		event.addMSEZ(BOW, bowMovesets);

		event.addProvider(AXE, default1HWieldStyle);
		event.addMSEZ(AXE, axeMovesets);

		event.addProvider(SPELL, default1HWieldStyle);
		event.addMSEZ(SPELL, spellMovesets);

		event.addProvider(SPEAR, default1HWieldStyle);
		event.addMSEZ(SPEAR, spearMovesets);

		event.addProvider(DAGGER, default1HWieldStyle);
		event.addMSEZ(DAGGER, daggerMovesets);
	}
}

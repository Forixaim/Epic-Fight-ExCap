package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.api.events.MoveSetRegistryEvent;
import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.*;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponModificationRegistry
{
	public static void fullInject()
	{
		AxeAttacks.injectAttacks();
		BokkenAttacks.injectAttacks();
		SwordAttacks.injectAttacks();
		LongswordAttacks.injectAttacks();
		GreatswordAttacks.injectAttacks();
		SpearAttacks.injectAttacks();
		DaggerAttacks.injectAttacks();
		TachiAttacks.injectAttacks();
		MNASpellAttacks.Inject();
		BowAttacks.Inject();
		UchigatanaAttacks.injectAttacks();
	}

	@SubscribeEvent
	public static void RegisterWeaponCapabilityModification(FMLLoadCompleteEvent event)
	{
		event.enqueueWork(WeaponModificationRegistry::fullInject);
	}
}

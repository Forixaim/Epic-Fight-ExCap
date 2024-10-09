package net.forixaim.efm_ex.capabilities;


import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponModificationRegistry
{
	@SubscribeEvent
	public static void RegisterWeaponCapabilityModification(FMLCommonSetupEvent event)
	{
		BokkenAttacks.injectAttacks();
		SwordAttacks.injectAttacks();
		LongswordAttacks.injectAttacks();
		GreatswordAttacks.injectAttacks();
		SpearAttacks.injectAttacks();
		DaggerAttacks.injectAttacks();
		TachiAttacks.injectAttacks();
	}
}

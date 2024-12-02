package net.forixaim.efm_ex.animations;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.animations.mna.MNAAnimations;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnimRegistry
{
	@SubscribeEvent
	public static void RegisterAnims(AnimationRegistryEvent event)
	{
		event.getRegistryMap().put(EpicFightEXCapability.MODID, AnimRegistry::Build);
	}

	public static void Build()
	{
		MNAAnimations.Build();
	}
}

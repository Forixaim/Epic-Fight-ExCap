package net.forixaim.efm_ex.animations;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.animations.mna.MNAAnimations;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;


@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnimRegistry
{



	@SubscribeEvent
	public static void RegisterAnims(AnimationManager.AnimationRegistryEvent event)
	{
		event.newBuilder(EpicFightEXCapability.MODID, AnimRegistry::RegisterAnimsBuild);
	}


	public static void RegisterAnimsBuild(AnimationManager.AnimationBuilder event)
	{

		MNAAnimations.Build(event);

	}
}

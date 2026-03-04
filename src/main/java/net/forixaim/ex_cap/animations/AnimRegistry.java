package net.forixaim.ex_cap.animations;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import yesman.epicfight.api.animation.AnimationManager;


@EventBusSubscriber(modid = EpicFightEXCapability.MODID)
public class AnimRegistry
{



	@SubscribeEvent
	public static void RegisterAnims(AnimationManager.AnimationRegistryEvent event)
	{
		event.newBuilder(EpicFightEXCapability.MODID, AnimRegistry::RegisterAnimsBuild);
	}


	public static void RegisterAnimsBuild(AnimationManager.AnimationBuilder event)
	{


	}
}

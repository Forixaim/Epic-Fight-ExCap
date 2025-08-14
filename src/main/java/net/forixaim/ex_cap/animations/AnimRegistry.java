package net.forixaim.ex_cap.animations;

import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.animations.mna.MNAAnimations;
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

package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.FistType;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.LongswordType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FistAttacks
{
	public static MoveSet DefaultFistMoveset;

	@SubscribeEvent
	public static void registerMovesets(FMLCommonSetupEvent event)
	{
		DefaultFistMoveset = MoveSet.createMoveSet(CapabilityItem.Styles.ONE_HAND)
				.addAttackAnimation(Animations.FIST_AUTO1, Animations.FIST_AUTO2, Animations.FIST_AUTO3, Animations.FIST_DASH, Animations.FIST_AIR_SLASH)
				.innateSkill(itemStack -> EpicFightSkills.RELENTLESS_COMBO);

		FistType.getInstance().getAttackSets().add(FistAttacks.DefaultFistMoveset);
	}
}

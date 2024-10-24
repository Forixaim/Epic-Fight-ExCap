package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.GreatswordType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GreatswordAttacks
{
	public static  MoveSet THGS;
	public static void injectAttacks()
	{

	}

	@SubscribeEvent
	public static void registerMovesets(FMLCommonSetupEvent event)
	{
		THGS = MoveSet.createMoveSet(CapabilityItem.Styles.TWO_HAND)
				.addAttackAnimation(Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH
				)
				.addLMM(LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
				.addLMM(LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
				.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
				.addLMMRecursive(Animations.BIPED_HOLD_GREATSWORD,
						LivingMotions.IDLE,
						LivingMotions.SNEAK,
						LivingMotions.KNEEL,
						LivingMotions.JUMP,
						LivingMotions.SWIM,
						LivingMotions.FLY,
						LivingMotions.CREATIVE_FLY,
						LivingMotions.CREATIVE_IDLE

				)
				.addLMM(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
				.innateSkill(itemStack -> EpicFightSkills.STEEL_WHIRLWIND)
		;
		GreatswordType.getInstance().getAttackSets().add(GreatswordAttacks.THGS);
	}
}

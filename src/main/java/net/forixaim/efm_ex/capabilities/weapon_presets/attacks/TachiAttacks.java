package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.TachiType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class TachiAttacks
{
	public static void injectAttacks()
	{
		TachiType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, TachiAttacks.defaultTwoHandAttackCycle));
		TachiType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordAttack));

	}

	public static Function<Pair<Style, EXWeaponCapability.Builder>,EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();

		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.FLOAT, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.FALL, Animations.BIPED_HOLD_TACHI)
				.livingMotionModifier(style, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
		builder.innateSkill(style, (itemstack) -> EpicFightSkills.RUSHING_TEMPO);
		builder.newStyleCombo(style, Animations.TACHI_AUTO1, Animations.TACHI_AUTO2, Animations.TACHI_AUTO3, Animations.TACHI_DASH, Animations.LONGSWORD_AIR_SLASH);

		return builder;
	};
}

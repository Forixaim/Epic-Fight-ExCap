package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SwordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class SwordAttacks
{
	public static void injectAttacks()
	{
		SwordType.getInstance().getAttackSets().add(OneHandedMoveset);
		SwordType.getInstance().getAttackSets().add(DualWieldedMoveset);
		SwordType.getInstance().getAttackSets().add(MountedAttacks.SwordMountMoveset);
	}

	public static final MoveSet OneHandedMoveset = MoveSet.createMoveSet(CapabilityItem.Styles.ONE_HAND)
			.addLMM(LivingMotions.BLOCK, Animations.SWORD_GUARD)
			.addAttackAnimation(Animations.SWORD_AUTO1,
					Animations.SWORD_AUTO2,
					Animations.SWORD_AUTO3,
					Animations.SWORD_DASH,
					Animations.SWORD_AIR_SLASH)
			.innateSkill(itemStack -> EpicFightSkills.SWEEPING_EDGE);



	public static final MoveSet DualWieldedMoveset = MoveSet.createMoveSet(CapabilityItem.Styles.TWO_HAND)
			.addLMM(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
			.addLMM(LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
			.addLMMRecursive(Animations.BIPED_HOLD_DUAL_WEAPON,
					LivingMotions.IDLE,
					LivingMotions.WALK,
					LivingMotions.CHASE,
					LivingMotions.KNEEL,
					LivingMotions.SNEAK,
					LivingMotions.SWIM,
					LivingMotions.FLOAT,
					LivingMotions.FALL)
			.addAttackAnimation(Animations.SWORD_AUTO1,
					Animations.SWORD_AUTO2,
					Animations.SWORD_AUTO3,
					Animations.SWORD_DASH,
					Animations.SWORD_AIR_SLASH)
			.innateSkill(itemStack -> EpicFightSkills.DANCING_EDGE);

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultOneHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.SWORD_GUARD);
		builder.newStyleCombo(style,
				Animations.SWORD_AUTO1,
				Animations.SWORD_AUTO2,
				Animations.SWORD_AUTO3,
				Animations.SWORD_DASH,
				Animations.SWORD_AIR_SLASH);
		builder.innateSkill(style, (itemStack -> EpicFightSkills.SWEEPING_EDGE));
		return builder;
	};

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD);
		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_RUN_DUAL);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.FLOAT, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.livingMotionModifier(style, LivingMotions.FALL, Animations.BIPED_HOLD_DUAL_WEAPON);
		builder.newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.SWORD_DUAL_AUTO1, Animations.SWORD_DUAL_AUTO2, Animations.SWORD_DUAL_AUTO3, Animations.SWORD_DUAL_DASH, Animations.SWORD_DUAL_AIR_SLASH);
		builder.innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.DANCING_EDGE);
		return builder;
	};
}

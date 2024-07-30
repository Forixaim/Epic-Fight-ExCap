package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.LongswordType;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.SwordType;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class LongswordAttacks
{
	public static void injectAttacks()
	{
		LongswordType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.OCHS, LongswordAttacks.LiechtenauerAttackCycle));
		LongswordType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, LongswordAttacks.defaultTwoHandAttackCycle));
		LongswordType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, LongswordAttacks.defaultTwoHandAttackCycle));
		LongswordType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordAttack));

	}

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultTwoHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.JUMP, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
		builder.newStyleCombo(style,
				Animations.LONGSWORD_AUTO1,
				Animations.LONGSWORD_AUTO2,
				Animations.LONGSWORD_AUTO3,
				Animations.LONGSWORD_DASH,
				Animations.LONGSWORD_AIR_SLASH
		);
		builder.innateSkill(style, (itemstack) -> EpicFightSkills.LIECHTENAUER);
		return builder;
	};

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> defaultOneHandAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.JUMP, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_LONGSWORD);
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
		builder.newStyleCombo(style,
				Animations.LONGSWORD_AUTO1,
				Animations.LONGSWORD_AUTO2,
				Animations.LONGSWORD_AUTO3,
				Animations.LONGSWORD_DASH,
				Animations.LONGSWORD_AIR_SLASH
		);
		builder.innateSkill(style, (itemstack) -> EpicFightSkills.SHARP_STAB);
		return builder;
	};

	public static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> LiechtenauerAttackCycle = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_WALK_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_WALK_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.JUMP, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_LIECHTENAUER);
		builder.livingMotionModifier(style, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);
		builder.newStyleCombo(style,
				Animations.LONGSWORD_LIECHTENAUER_AUTO1,
				Animations.LONGSWORD_LIECHTENAUER_AUTO2,
				Animations.LONGSWORD_LIECHTENAUER_AUTO3,
				Animations.LONGSWORD_DASH,
				Animations.LONGSWORD_AIR_SLASH
		);
		builder.innateSkill(style, (itemstack) -> EpicFightSkills.SHARP_STAB);
		return builder;
	};
}

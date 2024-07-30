package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.BokkenType;
import net.forixaim.efm_ex.capabilities.weapon_styles.ExampleStyle;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class BokkenAttacks
{

	public static void injectAttacks()
	{
		BokkenType.getInstance().getStyleComboProviderRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add("heavy", ProviderConditionalType.SKILL_EXISTENCE, SkillSlots.PASSIVE1, EpicFightSkills.BERSERKER, ExampleStyle.HEAVY, false));
		BokkenType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(ExampleStyle.HEAVY, heavyAttackCycle));
		BokkenType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, SwordAttacks.defaultOneHandAttackCycle));
		BokkenType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, SwordAttacks.defaultTwoHandAttackCycle));
	}

	private static final Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> heavyAttackCycle = (styleBuilderPair ->
	{
		EXWeaponCapability.Builder builder = styleBuilderPair.getSecond();
		Style style = styleBuilderPair.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD)
				.livingMotionModifier(style, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
				.newStyleCombo(style, Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH)
				.innateSkill(style, (itemstack) -> EpicFightSkills.STEEL_WHIRLWIND);
		return builder;
	});
}

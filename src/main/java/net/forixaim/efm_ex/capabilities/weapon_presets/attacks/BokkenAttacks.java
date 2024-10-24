package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.BokkenType;
import net.forixaim.efm_ex.capabilities.weapon_styles.ExampleStyle;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.List;
import java.util.function.Function;

public class BokkenAttacks
{

	public static void injectAttacks()
	{
		BokkenType.getInstance().getStyleComboProviderRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add("heavy", ProviderConditionalType.SKILL_EXISTENCE, SkillSlots.PASSIVE1, EpicFightSkills.BERSERKER, ExampleStyle.HEAVY, false, null));
		BokkenType.getInstance().getAttackSets().add(SwordAttacks.OneHandedMoveset);
		BokkenType.getInstance().getAttackSets().add(SwordAttacks.DualWieldedMoveset);
	}


}

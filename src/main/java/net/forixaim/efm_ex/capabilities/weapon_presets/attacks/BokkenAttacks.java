package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
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
		BokkenType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, SwordAttacks.sword1HMS);
		BokkenType.getInstance().getAttackSets().put(CapabilityItem.Styles.TWO_HAND, SwordAttacks.sword2HMS);
	}

}

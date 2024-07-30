package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class DaggerType extends CoreCapability
{
	private static DaggerType instance;

	public DaggerType()
	{
		instance = this;
	}

	public static DaggerType getInstance()
	{
		return instance;
	}
	public void init()
	{
		if (init)
			return;
		init = true;
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_daggers", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.TWO_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.DAGGER,
				EpicFightSounds.WHOOSH_SMALL.get(),
				EpicFightSounds.BLADE_HIT.get()
		).redirectedCollider(ColliderPreset.DAGGER)
				.redirectedPredicator(provider.exportCombination())
				.redirectedProvider(provider.exportStyle());
	}
}

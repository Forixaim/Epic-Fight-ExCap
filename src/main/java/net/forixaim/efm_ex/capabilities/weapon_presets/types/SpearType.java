package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class SpearType extends CoreCapability
{
	private static SpearType instance;

	public SpearType()
	{
		instance = this;
	}

	public static SpearType getInstance()
	{
		return instance;
	}
	public void init()
	{
		if (init)
			return;
		init = true;
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("shielded", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.SHIELD, CapabilityItem.Styles.ONE_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
						CapabilityItem.WeaponCategories.SPEAR,
						EpicFightSounds.WHOOSH.get(),
						EpicFightSounds.BLADE_HIT.get()
				).redirectedCollider(ColliderPreset.SPEAR)
				.redirectedPredicator(provider.exportCombination())
				.redirectedProvider(provider.exportStyle());
	}
}

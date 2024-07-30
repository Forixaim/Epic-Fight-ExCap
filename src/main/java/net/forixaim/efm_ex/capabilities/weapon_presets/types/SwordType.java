package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class SwordType extends CoreCapability
{
	private static SwordType instance;

	public SwordType()
	{
		instance = this;
	}

	public static SwordType getInstance()
	{
		return instance;
	}

	public void init()
	{
		if (init)
			return;
		init = true;
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_swords", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.SWORD, CapabilityItem.Styles.TWO_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.ONE_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.SWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).redirectedCollider(ColliderPreset.SWORD)
				.redirectedPredicator(provider.exportCombination())
				.redirectedProvider(provider.exportStyle());
	}
}

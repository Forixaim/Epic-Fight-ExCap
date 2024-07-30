package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class TachiType extends CoreCapability
{
	private static TachiType instance;

	public TachiType()
	{
		instance = this;
	}

	public static TachiType getInstance()
	{
		return instance;
	}

	public void init()
	{
		if (init)
			return;
		init = true;
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.TACHI,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).redirectedCollider(ColliderPreset.TACHI)
				.redirectedPredicator(provider.exportCombination())
				.redirectedProvider(provider.exportStyle());
	}
}

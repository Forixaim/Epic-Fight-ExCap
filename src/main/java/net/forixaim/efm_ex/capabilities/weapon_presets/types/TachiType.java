package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class TachiType extends CoreCapability
{
	private static final TachiType instance = new TachiType();

	private TachiType() {
		init();
	}

	public static TachiType getInstance()
	{
		return instance;
	}

	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.TACHI,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get())
				.collider(ColliderPreset.TACHI)
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

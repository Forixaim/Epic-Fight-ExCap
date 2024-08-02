package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class GreatswordType extends CoreCapability
{
	private static final GreatswordType instance = new GreatswordType();

	private GreatswordType() {
		init();
	}

	public static GreatswordType getInstance()
	{
		return instance;
	}
	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, true));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.LONGSWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.LONGSWORD)
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

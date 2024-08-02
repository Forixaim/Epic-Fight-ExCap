package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class DaggerType extends CoreCapability
{
	private static final DaggerType instance = new DaggerType();

	private DaggerType() {
		init();
	}

	public static DaggerType getInstance()
	{
		return instance;
	}
	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_daggers", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.TWO_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.DAGGER,
				EpicFightSounds.WHOOSH_SMALL.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.DAGGER)
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

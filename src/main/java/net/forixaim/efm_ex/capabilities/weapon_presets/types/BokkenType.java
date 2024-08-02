package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class BokkenType extends CoreCapability
{
	private static final BokkenType instance = new BokkenType();

	private BokkenType() {
		init();
	}

	public static BokkenType getInstance()
	{
		return instance;
	}

	/**
	 * Should not be called more than once
	 */
	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_swords", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.SWORD, CapabilityItem.Styles.TWO_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.ONE_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.SWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLUNT_HIT.get()
		).collider(ColliderPreset.SWORD)
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

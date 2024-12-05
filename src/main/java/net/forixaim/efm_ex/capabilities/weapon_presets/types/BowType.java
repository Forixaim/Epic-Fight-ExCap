package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXBowWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXRangedWeaponCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class BowType extends CoreCapability
{
	private static final BowType instance = new BowType();

	public static BowType getInstance()
	{
		return instance;
	}

	private BowType()
	{
		this.builder = EXRangedWeaponCapability.builder();
		init();
	}

	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false, null));
		builder.initialSetup(
						CapabilityItem.WeaponCategories.RANGED,
						EpicFightSounds.WHOOSH_SMALL.get(),
						EpicFightSounds.BLUNT_HIT.get()
				).collider(ColliderPreset.FIST)
				.passiveProvider(provider.exportWeaponPassiveSkill())
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle())
				.constructor(EXBowWeaponCapability::new);
	}
}

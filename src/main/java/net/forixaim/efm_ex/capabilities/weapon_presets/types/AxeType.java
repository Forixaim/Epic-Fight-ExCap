package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class AxeType extends CoreCapability
{
	private static final AxeType instance = new AxeType();

	private AxeType()
	{
		init();
	}

	public static AxeType getInstance()
	{
		return instance;
	}

	private void init()
	{
		provider.addConditional(DefaultConditionals.default1HWieldStyle);
		builder.initialSetup(
						CapabilityItem.WeaponCategories.AXE,
						EpicFightSounds.WHOOSH.get(),
						EpicFightSounds.BLADE_HIT.get()
				).collider(ColliderPreset.TOOLS);
	}
}

package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.DualDaggers;

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
		provider.addDefaultConditional(DualDaggers)
				.addDefaultConditional(DefaultConditionals.default1HWieldStyle);
		builder.initialSetup(
				CapabilityItem.WeaponCategories.DAGGER,
				EpicFightSounds.WHOOSH_SMALL.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.DAGGER);
	}


}

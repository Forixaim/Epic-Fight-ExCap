package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.DualSwords;

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
		provider.addConditional(DualSwords)
				.addConditional(DefaultConditionals.default1HWieldStyle);
		builder.initialSetup(
				CapabilityItem.WeaponCategories.SWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLUNT_HIT.get()
		).collider(ColliderPreset.SWORD);
	}
}

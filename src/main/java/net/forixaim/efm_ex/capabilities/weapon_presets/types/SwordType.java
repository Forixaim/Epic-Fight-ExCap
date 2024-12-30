package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.DualSwords;

public class SwordType extends CoreCapability
{
	private static final SwordType instance = new SwordType();
	private SwordType() {
		init();
	}

	public static SwordType getInstance()
	{
		return instance;
	}

	private void init()
	{
		provider.addDefaultConditional(DualSwords)
				.addDefaultConditional(DefaultConditionals.default1HWieldStyle);
		builder.initialSetup(
				CapabilityItem.WeaponCategories.SWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.SWORD);
	}


}

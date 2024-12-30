package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.SwordShieldLS;

public class SpearType extends CoreCapability
{
	private static final SpearType instance = new SpearType();

	private SpearType() {
		init();
	}

	public static SpearType getInstance()
	{
		return instance;
	}

	private void init()
	{
		provider.addConditional(SwordShieldLS)
				.addConditional(DefaultConditionals.default2HWieldStyle);
		builder.initialSetup(
						CapabilityItem.WeaponCategories.SPEAR,
						EpicFightSounds.WHOOSH.get(),
						EpicFightSounds.BLADE_HIT.get()
				).collider(ColliderPreset.SPEAR);
	}
}

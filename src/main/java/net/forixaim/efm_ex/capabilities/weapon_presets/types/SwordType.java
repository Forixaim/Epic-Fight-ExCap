package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

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
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_swords", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.SWORD, CapabilityItem.Styles.TWO_HAND, true, null))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.ONE_HAND, false, null));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.SWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.SWORD)
				.passiveProvider(provider.exportWeaponPassiveSkill())
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

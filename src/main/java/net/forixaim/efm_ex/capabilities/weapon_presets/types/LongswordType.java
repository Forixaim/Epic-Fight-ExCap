package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class LongswordType extends CoreCapability
{
	private static final LongswordType instance = new LongswordType();

	private LongswordType() {
		init();
	}

	public static LongswordType getInstance()
	{
		return instance;
	}
	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("sword_shield", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.SHIELD, CapabilityItem.Styles.TWO_HAND, true))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("liech", ProviderConditionalType.SKILL_ACTIVATION, SkillSlots.WEAPON_INNATE, EpicFightSkills.LIECHTENAUER, CapabilityItem.Styles.OCHS, false))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false));
		builder.initialSetup(
				CapabilityItem.WeaponCategories.LONGSWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.LONGSWORD)
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

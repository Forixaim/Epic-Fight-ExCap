package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.LiechtenauerCondition;
import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.SwordShieldLS;

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
		provider.addConditional(LiechtenauerCondition)
				.addConditional(SwordShieldLS)
				.addConditional(DefaultConditionals.default2HWieldStyle);
		builder.initialSetup(
				CapabilityItem.WeaponCategories.LONGSWORD,
				EpicFightSounds.WHOOSH.get(),
				EpicFightSounds.BLADE_HIT.get()
		).collider(ColliderPreset.LONGSWORD);
	}


}

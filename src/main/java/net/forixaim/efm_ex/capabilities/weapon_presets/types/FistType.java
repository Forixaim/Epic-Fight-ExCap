package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import com.google.common.collect.Maps;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.HashMap;
import java.util.Map;

public class FistType extends CoreCapability
{
	private static final FistType instance = new FistType();

	private FistType() {
		init();
	}

	public static FistType getInstance()
	{
		return instance;
	}

	public Map<Style, MoveSet> mapSets()
	{
		Map<Style, MoveSet> map = Maps.newHashMap();
		if (!attackSets.isEmpty())
		{
			for (MoveSet attackSet : attackSets)
			{
				map.put(attackSet.getWieldStyle(), attackSet);
			}
		}
		return map;
	}

	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false, null));
		builder.initialSetup(
						CapabilityItem.WeaponCategories.FIST,
						EpicFightSounds.WHOOSH.get(),
						EpicFightSounds.BLUNT_HIT.get()
				).collider(ColliderPreset.FIST)
				.passiveProvider(provider.exportWeaponPassiveSkill())
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}
}

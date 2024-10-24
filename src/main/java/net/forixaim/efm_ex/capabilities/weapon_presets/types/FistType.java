package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FistType extends CoreCapability
{
	private static final FistType instance = new FistType();
	public Map<Style, List<AnimationProvider<?>>> baseNoItemAttackStyle = Maps.newHashMap();

	private FistType() {
		init();
	}

	public static FistType getInstance()
	{
		return instance;
	}
	private void init()
	{
		provider.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("dual_daggers", InteractionHand.OFF_HAND, CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.TWO_HAND, true, null))
				.addDefaultConditional(COMBO_PROVIDER_REGISTRY.add("default", CapabilityItem.Styles.TWO_HAND, false, null));
		builder.initialSetup(
						CapabilityItem.WeaponCategories.DAGGER,
						EpicFightSounds.WHOOSH_SMALL.get(),
						EpicFightSounds.BLADE_HIT.get()
				).collider(ColliderPreset.DAGGER)
				.passiveProvider(provider.exportWeaponPassiveSkill())
				.weaponCombinationPredicator(provider.exportCombination())
				.styleProvider(provider.exportStyle());
	}

	@Override
	public CapabilityItem.Builder export()
	{
		//TODO: Implement this.
		for (Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, Pair<EXWeaponCapability.Builder, Pair<List<AnimationProvider<?>>, Map<Style, Map<LivingMotion, AnimationProvider<?>>>>>>> a : getAttackCombinationRegistry())
		{
			baseNoItemAttackStyle.put(a.getFirst(),);
		}
		return super.export();
	}

	public Map<Style, List<AnimationProvider<?>>> getBaseNoItemAttackStyle()
	{
		return ImmutableMap.copyOf(baseNoItemAttackStyle);
	}
}

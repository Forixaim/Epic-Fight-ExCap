package net.forixaim.efm_ex.capabilities;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.CoreWeaponCapabilityProvider;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class CoreCapability
{
	protected final List<ProviderConditional> styleComboProviderRegistry = new ArrayList<>();
	protected final List<Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>>> attackCombinationRegistry = new ArrayList<>();
	protected final CoreWeaponCapabilityProvider provider = new CoreWeaponCapabilityProvider();

	protected Map<Style, MoveSet> AttackSets = Maps.newHashMap();
	protected EXWeaponCapability.Builder builder = EXWeaponCapability.builder();

	protected void registerAttackCombo()
	{
		for (Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>> weaponCombo : attackCombinationRegistry)
		{
			builder.createStyleCategory(weaponCombo.getFirst(), weaponCombo.getSecond());
		}
	}

	protected void registerProviderConditionals()
	{
		for (ProviderConditional conditional : styleComboProviderRegistry)
		{
			provider.addConditional(conditional);
		}
	}

	public Map<Style, MoveSet> getAttackSets() {
		return AttackSets;
	}

	public static CoreCapability quickStart(Consumer<EXWeaponCapability.Builder> quickStart)
	{
		return new CoreCapability().start(quickStart);
	}

	private CoreCapability start(Consumer<EXWeaponCapability.Builder> qs)
	{
		qs.accept(builder);
		return this;
	}

	public List<ProviderConditional> getStyleComboProviderRegistry()
	{
		return styleComboProviderRegistry;
	}

	public CapabilityItem.Builder export()
	{
		registerProviderConditionals();
		registerAttackCombo();
		AttackSets.forEach(((style, moveSet) -> builder.addMoveset(style, moveSet)));
		builder.styleProvider(provider.exportStyle()).weaponCombinationPredicator(provider.exportCombination());
		return builder;
	}
}

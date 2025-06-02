package net.forixaim.efm_ex.capabilities;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.CoreWeaponCapabilityProvider;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXBowWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class CoreCapability
{
	protected final List<ProviderConditional> styleComboProviderRegistry = new ArrayList<>();
	protected final Map<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>> attackCombinationRegistry = Maps.newHashMap();
	protected final CoreWeaponCapabilityProvider provider = new CoreWeaponCapabilityProvider();
	protected static final Map<Item, Item> sheathes = Maps.newHashMap();

	protected Map<Style, MoveSet> AttackSets = Maps.newHashMap();
	protected EXWeaponCapability.Builder builder = EXWeaponCapability.builder();

	public static void addSheath(Item target, Item sheath)
	{
		if (sheathes.containsKey(target))
			return;
		sheathes.put(target, sheath);
	}

	protected void registerAttackCombo()
	{
		attackCombinationRegistry.forEach((style, pairBuilderFunction) -> builder.createStyleCategory(style, pairBuilderFunction));
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

	public static CoreCapability quickStartBow(Consumer<EXBowWeaponCapability.Builder> quickStart)
	{
		return new CoreBowCapability().start(quickStart);
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

	public WeaponCapability.Builder export()
	{
		EXWeaponCapability.Builder exportBuilder = EXWeaponCapability.copy(builder);
		registerProviderConditionals();
		registerAttackCombo();
		for (Map.Entry<Item, Item> entry : sheathes.entrySet())
		{
			exportBuilder.addSheath(entry.getKey(), entry.getValue());
		}
		AttackSets.forEach((exportBuilder::addMoveset));
		exportBuilder.styleProvider(provider.exportStyle()).weaponCombinationPredicator(provider.exportCombination());

		return exportBuilder;
	}

	public CapabilityItem.Builder export(boolean test)
	{
		EXWeaponCapability.Builder exportBuilder = EXWeaponCapability.copy(builder);
		registerProviderConditionals();
		registerAttackCombo();
		for (Map.Entry<Item, Item> entry : sheathes.entrySet())
		{
			exportBuilder.addSheath(entry.getKey(), entry.getValue());
		}
		AttackSets.forEach((exportBuilder::addMoveset));
		exportBuilder.styleProvider(provider.exportStyle()).weaponCombinationPredicator(provider.exportCombination());

		return exportBuilder;
	}
}

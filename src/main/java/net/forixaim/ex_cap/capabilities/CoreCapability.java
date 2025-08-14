package net.forixaim.ex_cap.capabilities;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.ex_cap.api.providers.ProviderConditional;
import net.forixaim.ex_cap.api.providers.CoreWeaponCapabilityProvider;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.forixaim.ex_cap.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

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

	protected final Map<Attribute, ValueModifier> attModifiers = Maps.newHashMap();

	protected Map<Style, MoveSet> AttackSets = Maps.newHashMap();
	protected EXWeaponCapability.Builder builder = EXWeaponCapability.builder();

	CoreCapability(float pierceModifier, float impactModifier, float aoeModifier)
	{
		attModifiers.put(EpicFightAttributes.ARMOR_NEGATION.get(), ValueModifier.multiplier(pierceModifier));
		attModifiers.put(EpicFightAttributes.IMPACT.get(),  ValueModifier.multiplier(impactModifier));
		attModifiers.put(EpicFightAttributes.MAX_STRIKES.get(),  ValueModifier.multiplier(aoeModifier));

	}

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

	public Map<Attribute, ValueModifier> getAttModifiers() {
		return ImmutableMap.copyOf(attModifiers);
	}

	public Map<Style, MoveSet> getAttackSets() {
		return AttackSets;
	}

	@Deprecated(forRemoval = true)
	public static CoreCapability quickStart(Consumer<EXWeaponCapability.Builder> quickStart)
	{
		return quickStart(quickStart, 1f, 1f, 1f);
	}

	public static CoreCapability quickStart(Consumer<EXWeaponCapability.Builder> quickStart, float pierceModifier, float impactModifier, float aoeModifier)
	{
		return new CoreCapability(pierceModifier, impactModifier, aoeModifier).start(quickStart);
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

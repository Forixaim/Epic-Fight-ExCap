package net.forixaim.efm_ex.capabilities;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.api.providers.CapabilityRegistry;
import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.StyleComboProvider;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CoreCapability
{
	public static final CapabilityRegistry COMBO_PROVIDER_REGISTRY = CapabilityRegistry.create(EpicFightMod.MODID);
	protected final List<ProviderConditional> styleComboProviderRegistry = new ArrayList<>();
	protected final List<Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>>> attackCombinationRegistry = new ArrayList<>();
	protected final StyleComboProvider provider = new StyleComboProvider();
	protected final EXWeaponCapability.Builder builder = EXWeaponCapability.builder();

	private void registerAttackCombo()
	{
		for (Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>> weaponCombo : attackCombinationRegistry)
		{
			builder.createStyleCategory(weaponCombo.getFirst(), weaponCombo.getSecond());
		}
	}

	private void registerProviderConditionals()
	{
		for (ProviderConditional conditional : styleComboProviderRegistry)
		{
			provider.addConditional(conditional);
		}

	}

	public List<Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>>> getAttackCombinationRegistry()
	{
		return attackCombinationRegistry;
	}

	public List<ProviderConditional> getStyleComboProviderRegistry()
	{
		return styleComboProviderRegistry;
	}

	public CapabilityItem.Builder export()
	{
		registerProviderConditionals();
		registerAttackCombo();
		return builder;
	}
}

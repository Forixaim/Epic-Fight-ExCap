package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.compat.EXSpellCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MNASpellType extends CoreCapability
{
	private static final MNASpellType instance = new MNASpellType();

	private final List<Pair<Style, Function<Pair<Style, EXSpellCapability.Builder>, EXSpellCapability.Builder>>> castAnimRegistry = Lists.newArrayList();

	private MNASpellType() {
		builder = EXSpellCapability.builder();
		init();

	}

	protected void registerCastAnimations()
	{
		for (Pair<Style, Function<Pair<Style, EXSpellCapability.Builder>, EXSpellCapability.Builder>> castAnim : castAnimRegistry)
		{
			if (builder instanceof EXSpellCapability.Builder spellBuilder)
			{
				spellBuilder.addMNACastAnimations(castAnim.getFirst(), castAnim.getSecond());
			}
		}
	}

	public List<Pair<Style, Function<Pair<Style, EXSpellCapability.Builder>, EXSpellCapability.Builder>>> getCastAnimRegistry()
	{
		return castAnimRegistry;
	}

	public static MNASpellType getInstance()
	{
		return instance;
	}
	private void init()
	{
		provider.addConditional(DefaultConditionals.default1HWieldStyle);
		builder.initialSetup(
						CapabilityItem.WeaponCategories.FIST,
						EpicFightSounds.WHOOSH.get(),
						EpicFightSounds.BLUNT_HIT.get()
				).collider(ColliderPreset.FIST);
	}

	@Override
	public CapabilityItem.Builder export()
	{
		registerProviderConditionals();
		registerAttackCombo();
		registerCastAnimations();
		return builder;
	}
}

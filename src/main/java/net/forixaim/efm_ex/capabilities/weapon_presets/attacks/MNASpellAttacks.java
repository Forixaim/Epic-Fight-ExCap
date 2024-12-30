package net.forixaim.efm_ex.capabilities.weapon_presets.attacks;

import com.mna.api.spells.collections.Shapes;
import net.forixaim.efm_ex.animations.mna.MNAAnimations;
import net.forixaim.efm_ex.capabilities.movesets.MoveSet;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.MNASpellType;
import net.forixaim.efm_ex.capabilities.weaponcaps.compat.EXSpellCapability;
import com.mojang.datafixers.util.Pair;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class MNASpellAttacks
{

	public static void Inject()
	{
		MNASpellType.getInstance().getAttackSets().put(CapabilityItem.Styles.ONE_HAND, baseSpellMS);
		MNASpellType.getInstance().getCastAnimRegistry().add(Pair.of(CapabilityItem.Styles.ONE_HAND, baseCastAttacks));
	}

	public static final Function<Pair<Style, EXSpellCapability.Builder>, EXSpellCapability.Builder> baseCastAttacks = main ->
	{
		Style style = main.getFirst();
		EXSpellCapability.Builder builder = main.getSecond();
		builder.addMNACastAnim(style, Shapes.PROJECTILE, Animations.FIST_AUTO1);
		return main.getSecond();
	};

	public static final MoveSet baseSpellMS = MoveSet.builder()
			.addAutoAttacks(
					MNAAnimations.SPELL_AUTO1,
					MNAAnimations.SPELL_AUTO2,
					MNAAnimations.SPELL_AUTO3,
					Animations.FIST_DASH,
					Animations.FIST_AIR_SLASH
			)
			.addInnateSkill(itemStack -> EpicFightSkills.RELENTLESS_COMBO)
			.build();


}

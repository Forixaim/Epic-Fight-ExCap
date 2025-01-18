package net.forixaim.efm_ex.capabilities.weapon_presets;

import com.mna.api.ManaAndArtificeMod;
import com.mna.api.spells.collections.Shapes;
import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.animations.mna.MNAAnimations;
import net.forixaim.efm_ex.api.events.MoveSetDefinitionRegistryEvent;
import net.forixaim.efm_ex.api.moveset.CastingMoveSet;
import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.efm_ex.api.moveset.RangedMoveSet;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = EpicFightEXCapability.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CoreMovesets
{
	public static MoveSet.MoveSetBuilder mountedSwordMS;
	public static MoveSet.MoveSetBuilder greatsword2HMS;
	public static MoveSet.MoveSetBuilder axeOneHandMS;
	public static MoveSet.MoveSetBuilder rangedMoveSet;
	public static MoveSet.MoveSetBuilder mountedSpearMS;
	public static MoveSet.MoveSetBuilder longsword2HMS;
	public static MoveSet.MoveSetBuilder longsword1HMS;
	public static MoveSet.MoveSetBuilder LiechtenauerMS;
	public static MoveSet.MoveSetBuilder dagger1HMS;
	public static MoveSet.MoveSetBuilder dagger2HMS;
	public static MoveSet.MoveSetBuilder baseSpellMS;
	public static MoveSet.MoveSetBuilder spear2HMS;
	public static MoveSet.MoveSetBuilder spear1HMS;
	public static MoveSet.MoveSetBuilder sword1HMS;
	public static MoveSet.MoveSetBuilder sword2HMS;
	public static MoveSet.MoveSetBuilder tachi2HMS;
	public static MoveSet.MoveSetBuilder UchigatanaBase;
	public static MoveSet.MoveSetBuilder UchigatanaSheathed;

	@SubscribeEvent
	public static void registerMovesets(MoveSetDefinitionRegistryEvent event)
	{
		event.getMoveSets().put(EpicFightEXCapability.MODID, CoreMovesets::build);
	}

	public static void build()
	{
		mountedSwordMS = MoveSet.builder()
				.addAutoAttacks(Animations.SWORD_MOUNT_ATTACK);

		greatsword2HMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_GREATSWORD,
						LivingMotions.IDLE, LivingMotions.JUMP, LivingMotions.KNEEL, LivingMotions.SNEAK,
						LivingMotions.SWIM, LivingMotions.FLY, LivingMotions.CREATIVE_FLY, LivingMotions.CREATIVE_IDLE)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_GREATSWORD,
						LivingMotions.WALK,
						LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_GREATSWORD)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.GREATSWORD_GUARD)
				.addAutoAttacks(
						Animations.GREATSWORD_AUTO1,
						Animations.GREATSWORD_AUTO2,
						Animations.GREATSWORD_DASH,
						Animations.GREATSWORD_AIR_SLASH
				)
				.addInnateSkill(itemstack -> EpicFightSkills.STEEL_WHIRLWIND);

		axeOneHandMS = MoveSet.builder()
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.SWORD_GUARD)
				.addAutoAttacks(
						Animations.AXE_AUTO1,
						Animations.AXE_AUTO2,
						Animations.AXE_DASH,
						Animations.AXE_AIRSLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.GUILLOTINE_AXE);

		rangedMoveSet = RangedMoveSet.builder()
				.addRangedAttackModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
				.addRangedAttackModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
				.addRangedAttackModifier(LivingMotions.WALK, Animations.BIPED_WALK)
				.addRangedAttackModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
				.addAutoAttacks(Animations.FIST_AUTO1, Animations.FIST_AUTO2, Animations.FIST_AUTO3, Animations.FIST_DASH, Animations.FIST_AIR_SLASH);

		mountedSpearMS = MoveSet.builder()
				.addAutoAttacks(
						Animations.SPEAR_MOUNT_ATTACK
				);

		longsword2HMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_LONGSWORD,
						LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
						LivingMotions.JUMP, LivingMotions.SWIM)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_LONGSWORD,
						LivingMotions.WALK, LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_LONGSWORD)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.LONGSWORD_GUARD)
				.addAutoAttacks(
						Animations.LONGSWORD_AUTO1,
						Animations.LONGSWORD_AUTO2,
						Animations.LONGSWORD_AUTO3,
						Animations.LONGSWORD_DASH,
						Animations.LONGSWORD_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.LIECHTENAUER);

		longsword1HMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_LONGSWORD,
						LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
						LivingMotions.JUMP, LivingMotions.SWIM)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_LONGSWORD,
						LivingMotions.WALK, LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_LONGSWORD)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.LONGSWORD_GUARD)
				.addAutoAttacks(
						Animations.LONGSWORD_AUTO1,
						Animations.LONGSWORD_AUTO2, Animations.LONGSWORD_AUTO3,
						Animations.LONGSWORD_DASH, Animations.LONGSWORD_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.SHARP_STAB);

		LiechtenauerMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_LIECHTENAUER,
						LivingMotions.IDLE, LivingMotions.SNEAK, LivingMotions.KNEEL,
						LivingMotions.JUMP, LivingMotions.SWIM)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_LIECHTENAUER,
						LivingMotions.WALK, LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_HOLD_LIECHTENAUER)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.LONGSWORD_GUARD)
				.addAutoAttacks(
						Animations.LONGSWORD_LIECHTENAUER_AUTO1,
						Animations.LONGSWORD_LIECHTENAUER_AUTO2,
						Animations.LONGSWORD_LIECHTENAUER_AUTO3,
						Animations.LONGSWORD_DASH,
						Animations.LONGSWORD_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.SHARP_STAB);

		dagger1HMS = MoveSet.builder()
				.addAutoAttacks(
						Animations.DAGGER_AUTO1,
						Animations.DAGGER_AUTO2,
						Animations.DAGGER_AUTO3,
						Animations.DAGGER_DASH,
						Animations.DAGGER_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.EVISCERATE);

		dagger2HMS = MoveSet.builder()
				.addAutoAttacks(
						Animations.DAGGER_DUAL_AUTO1,
						Animations.DAGGER_DUAL_AUTO2,
						Animations.DAGGER_DUAL_AUTO3,
						Animations.DAGGER_DUAL_AUTO4,
						Animations.DAGGER_DUAL_DASH,
						Animations.DAGGER_DUAL_AIR_SLASH
				)
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_DUAL_WEAPON,
						LivingMotions.IDLE, LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE,
						LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_DUAL)
				.addInnateSkill(itemStack -> EpicFightSkills.BLADE_RUSH);

		if (ModList.get().isLoaded(ManaAndArtificeMod.ID)) {
			baseSpellMS = CastingMoveSet.builder()
					.addAutoAttacks(
							MNAAnimations.SPELL_AUTO1,
							MNAAnimations.SPELL_AUTO2,
							MNAAnimations.SPELL_AUTO3,
							Animations.FIST_DASH,
							Animations.FIST_AIR_SLASH
					)
					.addSpellAnimations(Shapes.PROJECTILE, Animations.FIST_AUTO1)
					.addInnateSkill(itemStack -> EpicFightSkills.RELENTLESS_COMBO);
		} else {
			baseSpellMS = null;
		}

		spear2HMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_SPEAR,
						LivingMotions.IDLE, LivingMotions.SWIM)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_SPEAR,
						LivingMotions.WALK, LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_SPEAR)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.SPEAR_GUARD)
				.addAutoAttacks(
						Animations.SPEAR_TWOHAND_AUTO1, Animations.SPEAR_TWOHAND_AUTO2,
						Animations.SPEAR_DASH,
						Animations.SPEAR_TWOHAND_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.GRASPING_SPIRE);

		spear1HMS = MoveSet.builder()
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_SPEAR)
				.addAutoAttacks(
						Animations.SPEAR_ONEHAND_AUTO,
						Animations.SPEAR_DASH,
						Animations.SPEAR_ONEHAND_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.HEARTPIERCER);

		sword1HMS = MoveSet.builder()
				.addAutoAttacks(
						Animations.SWORD_AUTO1,
						Animations.SWORD_AUTO2, Animations.SWORD_AUTO3,
						Animations.SWORD_DASH, Animations.SWORD_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.SWEEPING_EDGE);

		sword2HMS = MoveSet.builder()
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.SWORD_DUAL_GUARD)
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_DUAL_WEAPON,
						LivingMotions.IDLE, LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE,
						LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_DUAL)
				.addAutoAttacks(
						Animations.SWORD_DUAL_AUTO1,
						Animations.SWORD_DUAL_AUTO2, Animations.SWORD_DUAL_AUTO3,
						Animations.SWORD_DUAL_DASH, Animations.SWORD_DUAL_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.DANCING_EDGE);

		tachi2HMS = MoveSet.builder()
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_TACHI,
						LivingMotions.IDLE,
						LivingMotions.KNEEL, LivingMotions.WALK, LivingMotions.CHASE, LivingMotions.RUN,
						LivingMotions.SNEAK, LivingMotions.SWIM, LivingMotions.FLOAT, LivingMotions.FALL)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.LONGSWORD_GUARD)
				.addAutoAttacks(
						Animations.TACHI_AUTO1,
						Animations.TACHI_AUTO2,
						Animations.TACHI_AUTO3,
						Animations.TACHI_DASH,
						Animations.LONGSWORD_AIR_SLASH
				)
				.addInnateSkill(itemStack -> EpicFightSkills.RUSHING_TEMPO);

		UchigatanaBase = MoveSet.builder()
				.addAutoAttacks(Animations.UCHIGATANA_AUTO1, Animations.UCHIGATANA_AUTO2,
						Animations.UCHIGATANA_AUTO3, Animations.UCHIGATANA_DASH,
						Animations.UCHIGATANA_AIR_SLASH)
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_UCHIGATANA,
						LivingMotions.IDLE, LivingMotions.KNEEL,
						LivingMotions.SWIM, LivingMotions.FALL,
						LivingMotions.FALL)
				.addLivingMotionsRecursive(() -> Animations.BIPED_WALK_UCHIGATANA,
						LivingMotions.CHASE, LivingMotions.WALK, LivingMotions.SNEAK)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_UCHIGATANA)
				.addLivingMotionModifier(LivingMotions.BLOCK, () -> Animations.UCHIGATANA_GUARD)
				.addInnateSkill(itemStack -> EpicFightSkills.BATTOJUTSU)
				.setPassiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE);

		UchigatanaSheathed = MoveSet.builder()
				.addAutoAttacks(Animations.UCHIGATANA_SHEATHING_AUTO,
						Animations.UCHIGATANA_SHEATHING_DASH,
						Animations.UCHIGATANA_SHEATH_AIR_SLASH)
				.addLivingMotionsRecursive(() -> Animations.BIPED_HOLD_UCHIGATANA_SHEATHING,
						LivingMotions.IDLE, LivingMotions.KNEEL,
						LivingMotions.SWIM, LivingMotions.FALL,
						LivingMotions.FALL, LivingMotions.SNEAK, LivingMotions.CHASE)
				.addLivingMotionModifier(LivingMotions.WALK, () -> Animations.BIPED_WALK_UCHIGATANA_SHEATHING)
				.addLivingMotionModifier(LivingMotions.RUN, () -> Animations.BIPED_RUN_UCHIGATANA_SHEATHING)
				.addInnateSkill(itemStack -> EpicFightSkills.BATTOJUTSU)
				.setPassiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE);
	}
}

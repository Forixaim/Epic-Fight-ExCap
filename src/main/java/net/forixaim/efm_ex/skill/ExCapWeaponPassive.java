package net.forixaim.efm_ex.skill;

import net.forixaim.bs_api.BattleArtsAPI;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.battle_style.BattleStyle;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.BladeRushSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class ExCapWeaponPassive extends Skill
{

	public ExCapWeaponPassive(SkillBuilder<? extends Skill> builder)
	{
		super(builder);
	}

	public static SkillBuilder<Skill> createBuilder()
	{
		return new SkillBuilder<>().setCategory(SkillCategories.WEAPON_PASSIVE);
	}

	/**
	 * Super must be called.
	 * @param container self
	 */
	@Override
	public void onInitiate(SkillContainer container)
	{
		if (ModList.get().isLoaded(BattleArtsAPI.MOD_ID))
		{
			SkillContainer battleStyleContainer = container.getExecutor().getSkill(BattleArtsSkillSlots.BATTLE_STYLE);
			if (container.getExecutor() instanceof ServerPlayerPatch playerPatch && !battleStyleContainer.isEmpty())
			{
				if (battleStyleContainer.getSkill() instanceof BattleStyle battleStyle)
				{
					if (battleStyle.canModifyAttacks())
					{
						battleStyleContainer.requestExecute(playerPatch, null);
					}
					if (!battleStyle.getWeaponDrawAnimations().isEmpty())
					{
						if (battleStyle.getWeaponDrawAnimations().get(container.getExecutor().getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory()) != null)
						{
							container.getExecutor().playAnimationSynchronized(battleStyle.getWeaponDrawAnimations().get(
									container.getExecutor().getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory()
							), 0);
						}
					}
				}
			}
		}
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		if (container.getExecutor().isLogicalClient())
		{
			super.onRemoved(container);
		}
		if (!container.getExecutor().getSkill(SkillSlots.BASIC_ATTACK).hasSkill(EpicFightSkills.BASIC_ATTACK))
		{
			container.getExecutor().getSkill(SkillSlots.BASIC_ATTACK).setSkill(EpicFightSkills.BASIC_ATTACK);
		}
		if (!container.getExecutor().getSkill(SkillSlots.AIR_ATTACK).hasSkill(EpicFightSkills.AIR_ATTACK))
		{
			container.getExecutor().getSkill(SkillSlots.AIR_ATTACK).setSkill(EpicFightSkills.AIR_ATTACK);
		}
	}
}

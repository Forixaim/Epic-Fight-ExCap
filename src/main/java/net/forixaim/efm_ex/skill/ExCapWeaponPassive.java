package net.forixaim.efm_ex.skill;

import com.mojang.datafixers.util.Pair;
import net.forixaim.bs_api.BattleArtsAPI;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.battle_style.BattleStyle;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public class ExCapWeaponPassive extends Skill
{

	public ExCapWeaponPassive(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	public static Builder<Skill> createBuilder()
	{
		return new Skill.Builder<>().setCategory(SkillCategories.WEAPON_PASSIVE);
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
			SkillContainer battleStyleContainer = container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE);
			if (container.getExecuter() instanceof ServerPlayerPatch playerPatch && !battleStyleContainer.isEmpty())
			{
				if (battleStyleContainer.getSkill() instanceof BattleStyle battleStyle)
				{
					if (battleStyle.canModifyAttacks())
					{
						battleStyleContainer.requestExecute(playerPatch, null);
					}
					if (!battleStyle.getWeaponDrawAnimations().isEmpty())
					{
						for (Pair<WeaponCategory, AnimationProvider<?>> animationProviderPair : battleStyle.getWeaponDrawAnimations())
						{
							if (container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == animationProviderPair.getFirst())
							{
								container.getExecuter().playAnimationSynchronized(animationProviderPair.getSecond().get(), 0);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		if (container.getExecuter().isLogicalClient())
		{
			super.onRemoved(container);
		}
		if (!container.getExecuter().getSkill(SkillSlots.BASIC_ATTACK).hasSkill(EpicFightSkills.BASIC_ATTACK))
		{
			container.getExecuter().getSkill(SkillSlots.BASIC_ATTACK).setSkill(EpicFightSkills.BASIC_ATTACK);
		}
		if (!container.getExecuter().getSkill(SkillSlots.AIR_ATTACK).hasSkill(EpicFightSkills.AIR_ATTACK))
		{
			container.getExecuter().getSkill(SkillSlots.AIR_ATTACK).setSkill(EpicFightSkills.AIR_ATTACK);
		}
	}
}

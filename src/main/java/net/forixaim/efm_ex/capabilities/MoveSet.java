package net.forixaim.efm_ex.capabilities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.system.linux.Stat;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MoveSet
{
	private final Style wieldStyle;
	private final Map<LivingMotion, StaticAnimation> livingMotionModifiers;
	private final List<StaticAnimation> attackAnimations;
	private Function<ItemStack, Skill> weaponInnateSkill = null;

	private MoveSet(final Style wieldStyle)
	{
		this.wieldStyle = wieldStyle;
		this.livingMotionModifiers = Maps.newHashMap();
		this.attackAnimations = Lists.newArrayList();
	}

	public List<AnimationProvider<?>> exportAttackMotions()
	{
		List<AnimationProvider<?>> providers = Lists.newArrayList();
		for (StaticAnimation animation : attackAnimations)
		{
			providers.add(() -> animation);
		}
		return providers;
	}



	public Function<ItemStack, Skill> getWeaponInnateSkill()
	{
		return weaponInnateSkill;
	}

	public Style getWieldStyle()
	{
		return wieldStyle;
	}

	public Map<LivingMotion, StaticAnimation> getLivingMotionModifiers()
	{
		return livingMotionModifiers;
	}

	public List<StaticAnimation> getAttackAnimations()
	{
		return attackAnimations;
	}

	public static class Builder
	{
		private final Style wieldStyle;
		private final Map<LivingMotion, StaticAnimation> livingMotionModifiers = Maps.newHashMap();
		private final List<StaticAnimation> attackAnimations = Lists.newArrayList();

		public Builder(Style wieldStyle)
		{
			this.wieldStyle = wieldStyle;
		}

		public Builder addAttackAnimation(final StaticAnimation... animation)
		{
			for (StaticAnimation anim : animation)
			{
				if (anim == null)
				{
					LogUtils.getLogger().debug("null");
				}
				else
				{
					LogUtils.getLogger().debug("Adding animation {}", anim);
				}
			}
			attackAnimations.addAll(Arrays.asList(animation));
			return this;
		}

		public Builder addLMM(final LivingMotion motion, final StaticAnimation animation)
		{
			this.livingMotionModifiers.put(motion, animation);
			return this;
		}

		public Builder addLMMRecursive(final StaticAnimation animation, final LivingMotion... motions)
		{
			for (final LivingMotion motion : motions)
			{
				this.livingMotionModifiers.put(motion, animation);
			}
			return this;
		}

	}
}

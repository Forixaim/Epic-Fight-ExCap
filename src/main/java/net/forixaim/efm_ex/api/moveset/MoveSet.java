package net.forixaim.efm_ex.api.moveset;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class MoveSet 
{
    private final List<StaticAnimation> AutoAttackAnimations;
    private final Map<LivingMotion, StaticAnimation> LivingMotionModifiers;
    private final Function<ItemStack, Skill> WeaponInnateSkill;
    private final Map<GuardSkill, Map<GuardSkill.BlockType, List<StaticAnimation>>> GuardAnimations;
    private final Skill WeaponPassiveSkill;

    public MoveSet(MoveSetBuilder builder)
    {
        this.AutoAttackAnimations = builder.AutoAttackAnimations;
        this.LivingMotionModifiers = builder.LivingMotionModifiers;
        this.GuardAnimations = builder.GuardAnimations;
        this.WeaponInnateSkill = builder.WeaponInnateSkill;
        this.WeaponPassiveSkill = builder.WeaponPassiveSkill;
    }

    public static MoveSetBuilder builder()
    {
        return new MoveSetBuilder();
    }

    public Skill getWeaponPassiveSkill() {
        return WeaponPassiveSkill;
    }

    public Function<ItemStack, Skill> getWeaponInnateSkill() {
        return WeaponInnateSkill;
    }

    public Map<GuardSkill, Map<GuardSkill.BlockType, List<StaticAnimation>>>  getGuardAnimations() {
        return GuardAnimations;
    }

    public List<StaticAnimation> getAutoAttackAnimations() {
        return AutoAttackAnimations;
    }

    public Map<LivingMotion, StaticAnimation> getLivingMotionModifiers() {
        return LivingMotionModifiers;
    }

    public static class MoveSetBuilder
    {
        protected final List<StaticAnimation> AutoAttackAnimations;
        protected final Map<LivingMotion, StaticAnimation> LivingMotionModifiers;
        protected Function<ItemStack, Skill> WeaponInnateSkill;
        protected final Map<GuardSkill, Map<GuardSkill.BlockType, List<StaticAnimation>>>  GuardAnimations;
        protected Skill WeaponPassiveSkill;

        public MoveSetBuilder()
        {
            AutoAttackAnimations = Lists.newArrayList();
            LivingMotionModifiers = Maps.newHashMap();
            GuardAnimations = Maps.newHashMap();
            WeaponInnateSkill = null;
            WeaponPassiveSkill = null;
        }

        public MoveSetBuilder setPassiveSkill(Skill newPassiveSkill)
        {
            this.WeaponPassiveSkill = newPassiveSkill;
            return this;
        }

        public MoveSetBuilder addAutoAttacks(StaticAnimation... attackAnimations)
        {
            AutoAttackAnimations.addAll(Arrays.asList(attackAnimations));
            return this;
        }

        public MoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, StaticAnimation animation)
        {
            LivingMotionModifiers.put(livingMotion, animation);
            return this;
        }

        public MoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            WeaponInnateSkill = weaponInnateSkill;
            return this;
        }

        public MoveSetBuilder addLivingMotionsRecursive(StaticAnimation animation, LivingMotion... motions)
        {
            for (LivingMotion livingMotion : motions)
            {
                LivingMotionModifiers.put(livingMotion, animation);
            }
            return this;
        }

        public MoveSetBuilder addGuardAnimations(Skill guardSkill, GuardSkill.BlockType blockType, StaticAnimation... animation)
        {
            if (guardSkill instanceof GuardSkill)
            {
                GuardAnimations.computeIfAbsent((GuardSkill) guardSkill, (guardSkill1 -> Maps.newHashMap())).computeIfAbsent(blockType, blockType1 -> Lists.newArrayList()).addAll(Arrays.asList(animation));
            }

            return this;
        }

        public MoveSetBuilder easyAddGuardAnimations(Skill guardSkill, Map<GuardSkill.BlockType, StaticAnimation> animations)
        {
            animations.forEach((blockType, animation) -> this.addGuardAnimations(guardSkill, blockType, animation));
            return this;
        }

        public MoveSet build()
        {
            return new MoveSet(this);
        }
    }
}

package net.forixaim.efm_ex.capabilities.movesets;

import com.google.common.collect.Maps;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.Map;
import java.util.function.Function;

public class RangedMoveSet extends MoveSet
{
    protected final Map<LivingMotion, AnimationProvider<?>> rangedAttackModifiers;

    public RangedMoveSet(MoveSetBuilder builder) {
        super(builder);
        this.rangedAttackModifiers = builder.rangedAttackModifiers;
    }

    public static MoveSetBuilder builder()
    {
        return new MoveSetBuilder();
    }

    public Map<LivingMotion, AnimationProvider<?>> getRangedAttackModifiers()
    {
        return rangedAttackModifiers;
    }

    public static class MoveSetBuilder extends MoveSet.MoveSetBuilder
    {
        protected final Map<LivingMotion, AnimationProvider<?>> rangedAttackModifiers;
        public MoveSetBuilder()
        {
            super();
            rangedAttackModifiers = Maps.newHashMap();
        }

        public MoveSetBuilder addRangedAttackModifier(LivingMotion livingMotion, AnimationProvider<?> provider)
        {
            rangedAttackModifiers.put(livingMotion, provider);
            return this;
        }

        public MoveSetBuilder setPassiveSkill(Skill newPassiveSkill)
        {
            return (MoveSetBuilder) super.setPassiveSkill(newPassiveSkill);
        }

        public MoveSetBuilder addAutoAttacks(StaticAnimation... attackAnimations)
        {
            return (MoveSetBuilder) super.addAutoAttacks(attackAnimations);
        }

        public MoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, StaticAnimation animation)
        {
            return (MoveSetBuilder) super.addLivingMotionModifier(livingMotion, animation);
        }

        public MoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            return (MoveSetBuilder) super.addInnateSkill(weaponInnateSkill);
        }

        public MoveSetBuilder addLivingMotionsRecursive(StaticAnimation animation, LivingMotion... motions)
        {
            return (MoveSetBuilder) super.addLivingMotionsRecursive(animation, motions);
        }

        public MoveSetBuilder addGuardAnimations(GuardSkill guardSkill, GuardSkill.BlockType blockType, StaticAnimation... animation)
        {
            return (MoveSetBuilder) super.addGuardAnimations(guardSkill, blockType, animation);
        }

        @Override
        public RangedMoveSet build() {
            return new RangedMoveSet( this);
        }

    }

}

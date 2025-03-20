package net.forixaim.efm_ex.api.moveset;

import com.google.common.collect.Maps;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.Map;
import java.util.function.Function;

public class RangedMoveSet extends MoveSet
{
    protected final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> rangedAttackModifiers;

    public RangedMoveSet(MoveSetBuilder builder) {
        super(builder);
        this.rangedAttackModifiers = builder.rangedAttackModifiers;
    }

    public static MoveSetBuilder builder()
    {
        return new MoveSetBuilder();
    }

    public Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getRangedAttackModifiers()
    {
        return rangedAttackModifiers;
    }

    public static class MoveSetBuilder extends MoveSet.MoveSetBuilder
    {
        protected final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> rangedAttackModifiers;
        public MoveSetBuilder()
        {
            super();
            rangedAttackModifiers = Maps.newHashMap();
        }

        public MoveSetBuilder addRangedAttackModifier(LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> provider)
        {
            rangedAttackModifiers.put(livingMotion, provider);
            return this;
        }

        public MoveSetBuilder setPassiveSkill(Skill newPassiveSkill)
        {
            return (MoveSetBuilder) super.setPassiveSkill(newPassiveSkill);
        }

        public MoveSetBuilder addAutoAttacks(AnimationManager.AnimationAccessor<? extends AttackAnimation>... attackAnimations)
        {
            return (MoveSetBuilder) super.addAutoAttacks(attackAnimations);
        }

        public MoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation)
        {
            return (MoveSetBuilder) super.addLivingMotionModifier(livingMotion, animation);
        }

        public MoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            return (MoveSetBuilder) super.addInnateSkill(weaponInnateSkill);
        }

        public MoveSetBuilder addLivingMotionsRecursive(AnimationManager.AnimationAccessor<? extends StaticAnimation> animation, LivingMotion... motions)
        {
            return (MoveSetBuilder) super.addLivingMotionsRecursive(animation, motions);
        }

        public MoveSetBuilder addGuardAnimations(GuardSkill guardSkill, GuardSkill.BlockType blockType, AnimationManager.AnimationAccessor<? extends StaticAnimation>... animation)
        {
            return (MoveSetBuilder) super.addGuardAnimations(guardSkill, blockType, animation);
        }

        @Override
        public RangedMoveSet build() {
            return new RangedMoveSet( this);
        }

    }

}

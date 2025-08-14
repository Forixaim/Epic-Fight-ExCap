package net.forixaim.ex_cap.api.moveset;

import com.google.common.collect.Maps;
import com.mna.api.spells.parts.Shape;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.Map;
import java.util.function.Function;

public class CastingMoveSet extends MoveSet
{
    protected final Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> spellAnimations;
    public CastingMoveSet(MoveSetBuilder builder) {
        super(builder);
        spellAnimations = builder.spellAnimations;
    }

    public static CastingMoveSet.MoveSetBuilder builder()
    {
        return new CastingMoveSet.MoveSetBuilder();
    }

    public Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getSpellAnimations() {
        return spellAnimations;
    }

    public static class MoveSetBuilder extends MoveSet.MoveSetBuilder
    {
        protected final Map<Shape, AnimationManager.AnimationAccessor<? extends StaticAnimation>> spellAnimations;
        public MoveSetBuilder()
        {
            super();
            spellAnimations = Maps.newHashMap();
        }

        public MoveSetBuilder addSpellAnimations(Shape shape, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation)
        {
            spellAnimations.put(shape, animation);
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

        @SafeVarargs
        public final MoveSetBuilder addGuardAnimations(GuardSkill guardSkill, GuardSkill.BlockType blockType, AnimationManager.AnimationAccessor<? extends StaticAnimation>... animation)
        {
            return (MoveSetBuilder) super.addGuardAnimations(guardSkill, blockType, animation);
        }

        @Override
        public CastingMoveSet build() {
            return new CastingMoveSet( this);
        }
    }
}

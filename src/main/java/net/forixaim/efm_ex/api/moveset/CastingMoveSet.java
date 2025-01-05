package net.forixaim.efm_ex.api.moveset;

import com.google.common.collect.Maps;
import com.mna.api.spells.parts.Shape;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class CastingMoveSet extends MoveSet
{
    protected final Map<Shape, StaticAnimation> spellAnimations;
    public CastingMoveSet(MoveSetBuilder builder) {
        super(builder);
        spellAnimations = builder.spellAnimations;
    }

    public static CastingMoveSet.MoveSetBuilder builder()
    {
        return new CastingMoveSet.MoveSetBuilder();
    }

    public Map<Shape, StaticAnimation> getSpellAnimations() {
        return spellAnimations;
    }

    public static class MoveSetBuilder extends MoveSet.MoveSetBuilder
    {
        protected final Map<Shape, StaticAnimation> spellAnimations;
        public MoveSetBuilder()
        {
            super();
            spellAnimations = Maps.newHashMap();
        }

        public MoveSetBuilder addSpellAnimations(Shape shape, StaticAnimation animation)
        {
            spellAnimations.put(shape, animation);
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

        public MoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, Supplier<StaticAnimation> animation)
        {
            return (MoveSetBuilder) super.addLivingMotionModifier(livingMotion, animation);
        }

        public MoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            return (MoveSetBuilder) super.addInnateSkill(weaponInnateSkill);
        }

        public MoveSetBuilder addLivingMotionsRecursive(Supplier<StaticAnimation> animation, LivingMotion... motions)
        {
            return (MoveSetBuilder) super.addLivingMotionsRecursive(animation, motions);
        }

        public MoveSetBuilder addGuardAnimations(GuardSkill guardSkill, GuardSkill.BlockType blockType, StaticAnimation... animation)
        {
            return (MoveSetBuilder) super.addGuardAnimations(guardSkill, blockType, animation);
        }

        @Override
        public CastingMoveSet build() {
            return new CastingMoveSet( this);
        }
    }
}

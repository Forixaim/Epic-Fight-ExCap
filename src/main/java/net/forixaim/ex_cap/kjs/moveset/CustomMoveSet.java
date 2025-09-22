package net.forixaim.ex_cap.kjs.moveset;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.forixaim.ex_cap.kjs.ExCapKubeJSPlugin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class CustomMoveSet extends MoveSet
{
    private final ResourceLocation id;
    // storage
    private final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> autoAttackAnimations;
    private final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> mountAttackAnimations;
    private final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> livingMotionModifiers;
    private Function<ItemStack, Skill> weaponInnateSkill;
    private final Map<Skill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> guardAnimations;
    private final Skill weaponPassiveSkill;
    private Predicate<LivingEntityPatch<?>> sheathRender;
    private AnimationManager.AnimationAccessor<? extends AttackAnimation> revelationAnimation;

    public CustomMoveSet(CustomMoveSetBuilder builder)
    {
        super(builder.toMainBuilder());
        this.id = builder.id;
        this.autoAttackAnimations = builder.autoAttackAnimations;
        this.mountAttackAnimations = builder.mountAttackAnimations;
        this.livingMotionModifiers = builder.livingMotionModifiers;
        this.weaponInnateSkill = builder.weaponInnateSkill;
        this.guardAnimations = builder.guardAnimations;
        this.weaponPassiveSkill = builder.weaponPassiveSkill;
        this.sheathRender = builder.sheathRender;
        this.revelationAnimation = builder.revelationAnimation;
    }

    public ResourceLocation getId()
    {
        return id;
    }

    public static class CustomMoveSetBuilder extends BuilderBase<MoveSet>
    {
        private final ResourceLocation id;
        // storage
        private final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> autoAttackAnimations;
        private final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> mountAttackAnimations;
        private final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> livingMotionModifiers;
        private Function<ItemStack, Skill> weaponInnateSkill;
        private final Map<Skill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> guardAnimations;
        private Skill weaponPassiveSkill;
        private Predicate<LivingEntityPatch<?>> sheathRender;
        private AnimationManager.AnimationAccessor<? extends AttackAnimation> revelationAnimation;

        public CustomMoveSetBuilder(ResourceLocation id)
        {
            super(id);
            this.id = id;
            autoAttackAnimations = Lists.newArrayList();
            mountAttackAnimations = Lists.newArrayList();
            livingMotionModifiers = Maps.newHashMap();
            weaponInnateSkill = null;
            guardAnimations = Maps.newHashMap();
            revelationAnimation = null;
            weaponInnateSkill =  null;
            sheathRender = livingEntityPatch -> false;
        }

        public CustomMoveSetBuilder revelationAttack(AnimationManager.AnimationAccessor<? extends AttackAnimation> attack)
        {
            revelationAnimation = attack;
            return this;
        }

        public CustomMoveSetBuilder shouldRenderSheath(Predicate<LivingEntityPatch<?>> sheathRender)
        {
            this.sheathRender = sheathRender;
            return this;
        }

        @Info("""
                So this is the passive skill
                """)
        public CustomMoveSetBuilder setPassiveSkill(Skill newPassiveSkill)
        {
            this.weaponPassiveSkill = newPassiveSkill;
            return this;
        }

        @SafeVarargs
        public final CustomMoveSetBuilder addMountAttacks(AnimationManager.AnimationAccessor<? extends AttackAnimation>... attackAnimations)
        {
            mountAttackAnimations.addAll(Arrays.asList(attackAnimations));
            return this;
        }

        public CustomMoveSetBuilder addAutoAttacks(AnimationManager.AnimationAccessor<? extends AttackAnimation>... attackAnimations)
        {
            autoAttackAnimations.addAll(Arrays.asList(attackAnimations));
            return this;
        }

        public CustomMoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation)
        {
            livingMotionModifiers.put(livingMotion, animation);
            return this;
        }

        public CustomMoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            this.weaponInnateSkill = weaponInnateSkill;
            return this;
        }

        public CustomMoveSetBuilder addLivingMotionsRecursive(AnimationManager.AnimationAccessor<? extends StaticAnimation> animation, LivingMotion... motions)
        {
            for (LivingMotion livingMotion : motions)
            {
                livingMotionModifiers.put(livingMotion, animation);
            }
            return this;
        }

        public MoveSetBuilder toMainBuilder()
        {
            MoveSetBuilder builder = MoveSet.builder();
            livingMotionModifiers.forEach(builder::addLivingMotionModifier);
            builder.addAutoAttacks(autoAttackAnimations.toArray(new AnimationManager.AnimationAccessor[0]));
            builder.setPassiveSkill(weaponPassiveSkill);
            builder.addInnateSkill(weaponInnateSkill);
            builder.addMountAttacks(mountAttackAnimations.toArray(new AnimationManager.AnimationAccessor[0]));
            builder.shouldRenderSheath(sheathRender);
            builder.revelationAttack(revelationAnimation);
            guardAnimations.forEach(builder::easyAddGuardAnimations);
            return builder;
        }

        public CustomMoveSet build() {
            return new CustomMoveSet(this);
        }

        @Override
        public RegistryInfo<MoveSet> getRegistryType()
        {
            return ExCapKubeJSPlugin.MOVESET_REGISTRY;
        }

        @Override
        public MoveSet createObject()
        {
            return this.build();
        }
    }
}

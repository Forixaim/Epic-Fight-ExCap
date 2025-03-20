package net.forixaim.efm_ex.api.moveset;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.forixaim.efm_ex.EpicFightEXCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryManager;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.datastruct.ClearableIdMapper;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MoveSet 
{
    private static final HashMultimap<Class<?>, MoveSet> MOVESETS = HashMultimap.create();
    private static final ResourceLocation CLASS_TO_MOVESET = new ResourceLocation(EpicFightEXCapability.MODID, "class_to_moveset");
    private static final ResourceLocation MOVESET_TO_ID = new ResourceLocation(EpicFightEXCapability.MODID, "moveset_to_id");

    private final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> AutoAttackAnimations;
    private final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> LivingMotionModifiers;
    private final Function<ItemStack, Skill> WeaponInnateSkill;
    private final Map<Skill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>> GuardAnimations;
    private final Skill WeaponPassiveSkill;
    private final AnimationManager.AnimationAccessor<? extends AttackAnimation> revelationAnimation;
    private final Predicate<LivingEntityPatch<?>> sheathRender;

    public static class MoveSetCallbacks implements IForgeRegistry.BakeCallback<MoveSet>, IForgeRegistry.CreateCallback<MoveSet>, IForgeRegistry.ClearCallback<MoveSet>
    {
        static final MoveSetCallbacks INSTANCE = new MoveSetCallbacks();

        @Override
        public void onBake(IForgeRegistryInternal<MoveSet> iForgeRegistryInternal, RegistryManager registryManager)
        {
            //What a bakery, no I'm not gonna show you my bakery.
        }

        @Override
        public void onClear(IForgeRegistryInternal<MoveSet> iForgeRegistryInternal, RegistryManager registryManager)
        {
        }

        @Override
        public void onCreate(IForgeRegistryInternal<MoveSet> iForgeRegistryInternal, RegistryManager registryManager)
        {
            iForgeRegistryInternal.setSlaveMap(CLASS_TO_MOVESET, Maps.newHashMap());
            iForgeRegistryInternal.setSlaveMap(MOVESET_TO_ID, new ClearableIdMapper<MoveSet>(iForgeRegistryInternal.getKeys().size()));
        }
    }

    public MoveSetCallbacks getCallbacks()
    {
        return MoveSetCallbacks.INSTANCE;
    }

    public MoveSet(MoveSetBuilder builder)
    {
        this.sheathRender = builder.sheathRender;
        this.AutoAttackAnimations = builder.AutoAttackAnimations;
        this.LivingMotionModifiers = builder.LivingMotionModifiers;
        this.GuardAnimations = builder.GuardAnimations;
        this.WeaponInnateSkill = builder.WeaponInnateSkill;
        this.WeaponPassiveSkill = builder.WeaponPassiveSkill;
        this.revelationAnimation = builder.revelationAnimation;
    }

    public AnimationManager.AnimationAccessor<? extends AttackAnimation> getRevelation()
    {
        return revelationAnimation;
    }

    public Predicate<LivingEntityPatch<?>> shouldRenderSheath()
    {
        return sheathRender;
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

    public Map<Skill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>  getGuardAnimations() {
        return GuardAnimations;
    }

    public List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> getAutoAttackAnimations() {
        return AutoAttackAnimations;
    }

    public Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifiers() {
        return LivingMotionModifiers;
    }

    public static class MoveSetBuilder
    {
        protected final List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> AutoAttackAnimations;
        protected final Map<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> LivingMotionModifiers;
        protected Function<ItemStack, Skill> WeaponInnateSkill;
        protected final Map<Skill, Map<GuardSkill.BlockType, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>  GuardAnimations;
        protected Skill WeaponPassiveSkill;
        protected Predicate<LivingEntityPatch<?>> sheathRender;
        protected AnimationManager.AnimationAccessor<? extends AttackAnimation> revelationAnimation;

        public MoveSetBuilder()
        {
            sheathRender = livingEntityPatch -> false;
            AutoAttackAnimations = Lists.newArrayList();
            LivingMotionModifiers = Maps.newHashMap();
            GuardAnimations = Maps.newHashMap();
            WeaponInnateSkill = null;
            WeaponPassiveSkill = null;
            revelationAnimation = null;
        }

        public MoveSetBuilder revelationAttack(AnimationManager.AnimationAccessor<? extends AttackAnimation> attack)
        {
            revelationAnimation = attack;
            return this;
        }

        public MoveSetBuilder shouldRenderSheath(Predicate<LivingEntityPatch<?>> sheathRender)
        {
            this.sheathRender = sheathRender;
            return this;
        }

        public MoveSetBuilder setPassiveSkill(Skill newPassiveSkill)
        {
            this.WeaponPassiveSkill = newPassiveSkill;
            return this;
        }

        public MoveSetBuilder addAutoAttacks(AnimationManager.AnimationAccessor<? extends AttackAnimation>... attackAnimations)
        {
            AutoAttackAnimations.addAll(Arrays.asList(attackAnimations));
            return this;
        }

        public MoveSetBuilder addLivingMotionModifier(LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation)
        {
            LivingMotionModifiers.put(livingMotion, animation);
            return this;
        }

        public MoveSetBuilder addInnateSkill(Function<ItemStack, Skill> weaponInnateSkill)
        {
            WeaponInnateSkill = weaponInnateSkill;
            return this;
        }

        public MoveSetBuilder addLivingMotionsRecursive(AnimationManager.AnimationAccessor<? extends StaticAnimation> animation, LivingMotion... motions)
        {
            for (LivingMotion livingMotion : motions)
            {
                LivingMotionModifiers.put(livingMotion, animation);
            }
            return this;
        }

        @SafeVarargs
        public final MoveSetBuilder addGuardAnimations(Skill guardSkill, GuardSkill.BlockType blockType, AnimationManager.AnimationAccessor<? extends StaticAnimation>... animation)
        {
            if (guardSkill instanceof GuardSkill)
            {
                GuardAnimations.computeIfAbsent((GuardSkill) guardSkill, (guardSkill1 -> Maps.newHashMap())).computeIfAbsent(blockType, blockType1 -> Lists.newArrayList()).addAll(Arrays.asList(animation));
            }

            return this;
        }

        public MoveSetBuilder easyAddGuardAnimations(Skill guardSkill, Map<GuardSkill.BlockType, AnimationManager.AnimationAccessor<? extends StaticAnimation>> animations)
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

package net.forixaim.efm_ex.capabilities.weaponcaps.compat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.compat.data.AnimConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class VOSWeaponCapabilityEX extends EXWeaponCapability
{
    protected final boolean directional;
    protected final Map<Style, List<AnimConfig>> animConfigs;

    protected VOSWeaponCapabilityEX(CapabilityItem.Builder builder) {
        super(builder);

        Builder weaponBuilder = (Builder)builder;
        this.directional = weaponBuilder.directional;
        this.animConfigs = weaponBuilder.animConfigMap;
    }

    public boolean isDirectional() {
        return this.directional;
    }

    public final List<AnimConfig> getAnimConfigs(PlayerPatch<?> playerPatch) {
        return this.animConfigs.get(this.getStyle(playerPatch));
    }

    public static EXWeaponCapability.Builder builder() {
        return new Builder();
    }

    public static class Builder extends EXWeaponCapability.Builder {
        boolean directional;
        Map<Style, List<AnimConfig>> animConfigMap;

        protected Builder() {
            super();
            this.directional = false;
            this.animConfigMap = Maps.newHashMap();
        }

        public Builder isDirectional(boolean value) {
            this.directional = value;
            return this;
        }

        public Builder newAnimConfig(Style style, AnimConfig... animConfigs) {
            this.animConfigMap.put(style, Lists.newArrayList(animConfigs));
            return this;
        }

        @Override
        public Builder constructor(Function<CapabilityItem.Builder, CapabilityItem> constructor) {
            return (Builder) super.constructor(constructor);
        }

        @Override
        public Builder category(WeaponCategory category) {
            return (Builder) super.category(category);
        }

        @Override
        public Builder styleProvider(Function<LivingEntityPatch<?>, Style> styleProvider) {
            return (Builder) super.styleProvider(styleProvider);
        }

        @Override
        public Builder passiveSkill(Skill passiveSkill) {
            return (Builder) super.passiveSkill(passiveSkill);
        }

        @Override
        public Builder swingSound(SoundEvent swingSound) {
            return (Builder) super.swingSound(swingSound);
        }

        @Override
        public Builder hitSound(SoundEvent hitSound) {
            return (Builder) super.hitSound(hitSound);
        }

        @Override
        public Builder hitParticle(HitParticleType hitParticle) {
            return (Builder) super.hitParticle(hitParticle);
        }

        @Override
        public Builder collider(Collider collider) {
            return (Builder) super.collider(collider);
        }

        @Override
        public Builder canBePlacedOffhand(boolean canBePlacedOffhand) {
            return (Builder) super.canBePlacedOffhand(canBePlacedOffhand);
        }

        @Override
        public Builder livingMotionModifier(Style wieldStyle, LivingMotion livingMotion, StaticAnimation animation) {
            return (Builder) super.livingMotionModifier(wieldStyle, livingMotion, animation);
        }

        @Override
        public Builder addStyleAttibutes(Style style, Pair<Attribute, AttributeModifier> attributePair) {
            return (Builder) super.addStyleAttibutes(style, attributePair);
        }

        @Override
        public Builder newStyleCombo(Style style, StaticAnimation... animation) {
            return (Builder) super.newStyleCombo(style, animation);
        }

        @Override
        public Builder weaponCombinationPredicator(Function<LivingEntityPatch<?>, Boolean> predicator) {
            return (Builder) super.weaponCombinationPredicator(predicator);
        }

        @Override
        public Builder innateSkill(Style style, Function<ItemStack, Skill> innateSkill) {
            return (Builder) super.innateSkill(style, innateSkill);
        }

        @Override
        public Builder comboCancel(Function<Style, Boolean> comboCancel) {
            return (Builder) super.comboCancel(comboCancel);
        }
    }
}

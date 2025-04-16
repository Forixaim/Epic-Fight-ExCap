package net.forixaim.efm_ex.api.animation.types;

import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.HurtableEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import java.util.*;

/**
 *
 */
public class KnockbackAttackAnimation extends AttackAnimation
{


	public KnockbackAttackAnimation(float transitionTime, float antic, float preDelay, float contact, float recovery, @Nullable Collider collider, Joint colliderJoint, AnimationManager.AnimationAccessor<? extends AttackAnimation> accessor, AssetAccessor<? extends Armature> armature) {
		super(transitionTime, antic, preDelay, contact, recovery, collider, colliderJoint, accessor, armature);
	}

	public KnockbackAttackAnimation(float transitionTime, float antic, float preDelay, float contact, float recovery, InteractionHand hand, @Nullable Collider collider, Joint colliderJoint, AnimationManager.AnimationAccessor<? extends AttackAnimation> accessor, AssetAccessor<? extends Armature> armature) {
		super(transitionTime, antic, preDelay, contact, recovery, hand, collider, colliderJoint, accessor, armature);
	}

	public KnockbackAttackAnimation(float transitionTime, AnimationManager.AnimationAccessor<? extends AttackAnimation> accessor, AssetAccessor<? extends Armature> armature, Phase... phases) {
		super(transitionTime, accessor, armature, phases);
	}

	public KnockbackAttackAnimation(float convertTime, float antic, float preDelay, float contact, float recovery, InteractionHand hand, @Nullable Collider collider, Joint colliderJoint, String path, AssetAccessor<? extends Armature> armature) {
		super(convertTime, antic, preDelay, contact, recovery, hand, collider, colliderJoint, path, armature);
	}

	public KnockbackAttackAnimation(float convertTime, String path, AssetAccessor<? extends Armature> armature, Phase... phases) {
		super(convertTime, path, armature, phases);
	}

	private static @NotNull Vec3f getLaunchDirection(KnockbackPhase knockbackPhase)
	{
		double horizontalRad = Math.toRadians(knockbackPhase.horizontalDegree);
		double verticalRad = Math.toRadians(knockbackPhase.verticalDegree);

		double forwardBackVelocity = Math.sin(horizontalRad);
		double sideVelocity = Math.cos(horizontalRad);
		double verticalVelocity = Math.sin(verticalRad);

		return new Vec3f((float)sideVelocity, (float)verticalVelocity, (float)forwardBackVelocity);
	}

	public static class KnockbackPhase extends AttackAnimation.Phase
	{

		public float horizontalDegree;
		public float verticalDegree;
		public boolean spike = false;

		public KnockbackPhase(float start, float antic, float contact, float recovery, float end, Joint joint, Collider collider, float horizontalDegree, float verticalDegree)
		{
			super(start, antic, contact, recovery, end, InteractionHand.MAIN_HAND, joint, collider);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
		}

		public KnockbackPhase(float start, float antic, float contact, float recovery, float end, InteractionHand hand, Joint joint, Collider collider, float horizontalDegree, float verticalDegree, boolean spike)
		{
			super(start, antic, antic, contact, recovery, end, hand, joint, collider);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
			this.spike = spike;
		}

		public KnockbackPhase(float start, float antic, float preDelay, float contact, float recovery, float end, Joint joint, Collider collider, float horizontalDegree, float verticalDegree, boolean spike)
		{
			super(start, antic, preDelay, contact, recovery, end, InteractionHand.MAIN_HAND, joint, collider);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
			this.spike = spike;
		}

		public KnockbackPhase(float start, float antic, float preDelay, float contact, float recovery, float end, InteractionHand hand, Joint joint, Collider collider, float horizontalDegree, float verticalDegree, boolean spike)
		{
			super(start, antic, preDelay, contact, recovery, end, false, hand, joint, collider);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
			this.spike = spike;
		}

		public KnockbackPhase(InteractionHand hand, Joint joint, Collider collider, float horizontalDegree, float verticalDegree)
		{
			super(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, true, hand, joint, collider);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;

		}

		public KnockbackPhase(float start, float antic, float preDelay, float contact, float recovery, float end, boolean noStateBind, InteractionHand hand, Joint joint, Collider collider, float horizontalDegree, float verticalDegree)
		{
			super(start, antic, preDelay, contact, recovery, end, noStateBind, hand, AttackAnimation.JointColliderPair.of(joint, collider));
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
		}

		public KnockbackPhase(float start, float antic, float preDelay, float contact, float recovery, float end, InteractionHand hand, float horizontalDegree, float verticalDegree, JointColliderPair... colliders)
		{
			super(start, antic, preDelay, contact, recovery, end, false, hand, colliders);
			this.horizontalDegree = horizontalDegree;
			this.verticalDegree = verticalDegree;
		}

		public KnockbackPhase(float start, float antic, float preDelay, float contact, float recovery, float end, boolean noStateBind, InteractionHand hand, JointColliderPair... colliders) {
			super(start, antic, preDelay, contact, recovery, end, noStateBind, hand, colliders);
		}

		public void spike()
		{
			spike = true;
		}


		@Override
		public <V> Phase addProperty(AnimationProperty.AttackPhaseProperty<V> propertyType, V value)
		{
			super.addProperty(propertyType, value);
			return this;
		}

		public List<Entity> getCollidingEntities(LivingEntityPatch<?> entitypatch, AttackAnimation animation, float prevElapsedTime, float elapsedTime, float attackSpeed)
		{
			Set<Entity> entities = Sets.newHashSet();
			JointColliderPair[] var7 = this.colliders;

            for (Pair<Joint, Collider> colliderInfo : var7) {
                Collider collider = colliderInfo.getSecond();
                if (collider == null) {
                    collider = entitypatch.getColliderMatching(this.hand);
                }

                entities.addAll(collider.updateAndSelectCollideEntity(entitypatch, animation, prevElapsedTime, elapsedTime, colliderInfo.getFirst(), attackSpeed));
            }

			return new ArrayList<>(entities);
		}

		public JointColliderPair[] getColliders()
		{
			return this.colliders;
		}

		public InteractionHand getHand()
		{
			return this.hand;
		}
	}
}

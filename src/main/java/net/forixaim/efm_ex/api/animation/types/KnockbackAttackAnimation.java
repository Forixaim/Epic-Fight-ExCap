package net.forixaim.efm_ex.api.animation.types;

import com.google.common.collect.Maps;
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
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
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
	public KnockbackAttackAnimation(float convertTime, float antic, float preDelay, float contact, float recovery, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature
			, float horizontalDegree, float verticalDegree)
	{
		super(convertTime, path, armature, new KnockbackPhase(0.0F, antic, preDelay, contact, recovery, Float.MAX_VALUE, colliderJoint, collider, horizontalDegree, verticalDegree, false));
	}

	public KnockbackAttackAnimation(float convertTime, float antic, float preDelay, float contact, float recovery, InteractionHand hand, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature, float horizontalDegree, float verticalDegree)
	{
		super(convertTime, path, armature, new KnockbackPhase(0.0F, antic, preDelay, contact, recovery, Float.MAX_VALUE, hand, colliderJoint, collider, horizontalDegree, verticalDegree, false));
	}

	public KnockbackAttackAnimation(float convertTime, float antic, float preDelay, float contact, float recovery, InteractionHand hand, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature, boolean noRegister, float horizontalDegree, float verticalDegree)
	{
		super(convertTime, path, armature, noRegister, new KnockbackPhase(0.0F, antic, preDelay, contact, recovery, Float.MAX_VALUE, hand, colliderJoint, collider, horizontalDegree, verticalDegree, false));
	}

	public KnockbackAttackAnimation(float convertTime, String path, Armature armature, float horizontalDegree, float verticalDegree, Phase... phases)
	{
		super(convertTime, path, armature, phases);
	}

	public KnockbackAttackAnimation(float convertTime, String path, Armature armature, boolean noRegister, float horizontalDegree, float verticalDegree, Phase... phases)
	{
		super(convertTime, path, armature, noRegister, phases);
	}

	@Override
	protected void hurtCollidingEntities(LivingEntityPatch<?> attackerPatch, float prevElapsedTime, float elapsedTime, EntityState prevState, EntityState state, Phase phase)
	{
		if (!(phase instanceof KnockbackPhase))
			super.hurtCollidingEntities(attackerPatch, prevElapsedTime, elapsedTime, prevState, state, phase);
		else
		{
			LivingEntity attacker = attackerPatch.getOriginal();
			float prevPoseTime = prevState.attacking() ? prevElapsedTime : phase.preDelay;
			float poseTime = state.attacking() ? elapsedTime : phase.contact;
			List<Entity> list = this.getPhaseByTime(elapsedTime).getCollidingEntities(attackerPatch, this, prevPoseTime, poseTime, this.getPlaySpeed(attackerPatch, this));
			if (!list.isEmpty())
			{
				HitEntityList hitEntities = new HitEntityList(attackerPatch, list, phase.getProperty(AnimationProperty.AttackPhaseProperty.HIT_PRIORITY).orElse(HitEntityList.Priority.DISTANCE));
				int maxStrikes = this.getMaxStrikes(attackerPatch, phase);

				while (true)
				{
					Entity target;
					LivingEntity trueTarget;
					//Teammate Check
					do
					{
						do
						{
							do
							{
								do
								{
									do
									{
										do
										{
											if (attackerPatch.getCurrenltyHurtEntities().size() >= maxStrikes || !hitEntities.next())
											{
												return;
											}

											target = hitEntities.getEntity();
											trueTarget = this.getTrueEntity(target);
										} while (trueTarget == null);
									} while (!trueTarget.isAlive());
								} while (attackerPatch.getCurrenltyAttackedEntities().contains(trueTarget));
							} while (attackerPatch.isTeammate(target));
						} while (!(target instanceof LivingEntity) && !(target instanceof PartEntity));
					} while (!attacker.hasLineOfSight(target));

					HurtableEntityPatch<?> hitHurtableEntityPatch = (HurtableEntityPatch<?>) EpicFightCapabilities.getEntityPatch(target, HurtableEntityPatch.class);
					EpicFightDamageSource source = this.getEpicFightDamageSource(attackerPatch, target, phase);

					int prevInvulTime = target.invulnerableTime;
					target.invulnerableTime = 0;
					AttackResult attackResult = attackerPatch.attack(source, target, phase.hand);
					target.invulnerableTime = prevInvulTime;
					if (attackResult.resultType.dealtDamage())
					{
						target.level().playSound(null, target.getX(), target.getY(), target.getZ(), this.getHitSound(attackerPatch, phase), target.getSoundSource(), 1.0F, 1.0F);
						this.spawnHitParticle((ServerLevel) target.level(), attackerPatch, target, phase);
						if (hitHurtableEntityPatch != null && phase.getProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE).isPresent() && !hitHurtableEntityPatch.getOriginal().hasEffect(EpicFightMobEffects.STUN_IMMUNITY.get()))
						{
							float stunTime;
							stunTime = (float) ((double) (source.getImpact() * 0.4F) * (1.0 - trueTarget.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)));
							if (hitHurtableEntityPatch.getOriginal().isAlive())
							{
								if (phase instanceof KnockbackPhase knockbackPhase)
								{
									//Convert the degrees to radians with attacker rotation prepared
									knockbackPhase.horizontalDegree -= attacker.getYRot();
									Vec3f launchDirection = getLaunchDirection(knockbackPhase);
									OpenMatrix4f playerModifier = new OpenMatrix4f().rotate(-((float)Math.toRadians(attackerPatch.getYRot())), new Vec3f(0.0F, 1.0F, 0.0F));
									OpenMatrix4f.transform3v(playerModifier, launchDirection, launchDirection);

									double power = source.getImpact() * 0.25F;
									double d1 = attacker.getX() - target.getX();
									double d2 = attacker.getY() - 8.0 - target.getY();

									double d0;
									for(d0 = attacker.getZ() - target.getZ(); d1 * d1 + d0 * d0 < 1.0E-4; d0 = (Math.random() - Math.random()) * 0.01) {
										d1 = (Math.random() - Math.random()) * 0.01;
									}

									if (knockbackPhase.spike)
									{
										power *= -1;
									}

									double knockbackPower = source.getImpact() * 0.25F;
									if (!(trueTarget instanceof Player))
									{
										knockbackPower *= 1.0 - trueTarget.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
									}
									if (knockbackPower > 0.0)
									{
										target.hasImpulse = true;
										Vec3 vec3 = attacker.getDeltaMovement();
										Vec3 vec31 = (new Vec3(d1, d2, d0)).normalize().scale(power);
										if (!(trueTarget instanceof Player) || !(attackerPatch instanceof PlayerPatch))
										{
											target.setDeltaMovement(vec3.x / 2.0 - vec31.x, vec3.y / 2.0 - vec31.y, vec3.z / 2.0 - vec31.z);
										}
									}
									if (trueTarget instanceof Player && attackerPatch instanceof PlayerPatch)
									{
										trueTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 5, (int) (knockbackPower * 4.0 * 6.0), true, false, false));
									}
									trueTarget.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) (knockbackPower * 4.0 * 6.0), 20, true, false, false));
								}

							}
						}
					}

					attackerPatch.getCurrenltyAttackedEntities().add(trueTarget);
					if (attackResult.resultType.shouldCount())
					{
						attackerPatch.getCurrenltyHurtEntities().add(trueTarget);
					}
				}
			}
		}
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
			int var8 = var7.length;

			for (int var9 = 0; var9 < var8; ++var9)
			{
				Pair<Joint, Collider> colliderInfo = var7[var9];
				Collider collider = (Collider) colliderInfo.getSecond();
				if (collider == null)
				{
					collider = entitypatch.getColliderMatching(this.hand);
				}

				entities.addAll(collider.updateAndSelectCollideEntity(entitypatch, animation, prevElapsedTime, elapsedTime, (Joint) colliderInfo.getFirst(), attackSpeed));
			}

			return new ArrayList(entities);
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

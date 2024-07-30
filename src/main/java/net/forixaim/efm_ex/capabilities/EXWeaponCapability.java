package net.forixaim.efm_ex.capabilities;


import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;

/**
 * A custom implementation of the original WeaponCapability class with a focus on modularity with a conditional passive skill provider.
 */
public class EXWeaponCapability extends WeaponCapability
{
	protected final Function<LivingEntityPatch<?>, Skill> passiveSkillProvider;
	protected EXWeaponCapability(CapabilityItem.Builder builder)
	{
		super(builder);

		Builder efbsBuilder = (Builder) builder;
		this.passiveSkillProvider = efbsBuilder.passiveSkillProvider;
	}

	public static Builder builder() {
		return new Builder();
	}
	/**
	 * The modified builder that allows for easier weapon style creation and conditional passive skill providers.
	 */
	public static class Builder extends WeaponCapability.Builder
	{
		Function<LivingEntityPatch<?>, Skill> passiveSkillProvider;

		protected Builder()
		{
			super();
		}

		public Builder initialSetup(WeaponCategory category, Skill passiveSkill, SoundEvent swingSound, SoundEvent hitSound, @NotNull Collider collider)
		{
			this.category(category);
			this.passiveSkill(passiveSkill);
			this.swingSound(swingSound);
			this.hitSound(hitSound);
			this.collider(collider);
			return this;
		}

		public Builder initialSetup(WeaponCategory category, SoundEvent swingSound, SoundEvent hitSound)
		{
			this.category(category);
			this.swingSound(swingSound);
			this.hitSound(hitSound);
			return this;
		}

		public Builder redirectedProvider(Function<LivingEntityPatch<?>, Style> styleProvider)
		{
			this.styleProvider(styleProvider);
			return this;
		}

		public Builder redirectedCategory(WeaponCategory category)
		{
			this.category(category);
			return this;
		}

		public Builder redirectedPassiveSkill(Skill passiveSkill)
		{
			this.passiveSkill(passiveSkill);
			return this;
		}

		public Builder redirectedSwingSound(SoundEvent swingSound)
		{
			this.swingSound(swingSound);
			return this;
		}

		public Builder redirectedHitSound(SoundEvent hitSound)
		{
			this.hitSound(hitSound);
			return this;
		}

		public Builder redirectedCollider(@NotNull Collider collider)
		{
			this.collider(collider);
			return this;
		}

		public Builder createStyleCategory(Style style, Function<Pair<Style, Builder>, Builder> weaponCombo)
		{
			return weaponCombo.apply(new Pair<>(style, this));

		}

		public Builder offHandUse(Boolean use)
		{
			this.canBePlacedOffhand(use);
			return this;
		}

		public Builder redirectedPredicator(Function<LivingEntityPatch<?>, Boolean> predicator)
		{
			this.weaponCombinationPredicator(predicator);
			return this;
		}
	}
}

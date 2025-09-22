package net.forixaim.ex_cap.kjs.moveset;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.forixaim.battle_arts_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.ex_cap.EpicFightEXCapability;
import net.forixaim.ex_cap.api.providers.HelperFunctions;
import net.forixaim.ex_cap.api.providers.ProviderConditional;
import net.forixaim.ex_cap.api.providers.ProviderConditionalType;
import net.forixaim.ex_cap.kjs.ExCapKubeJSPlugin;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Predicate;

public class ProviderConditionalJS extends ProviderConditional
{

	public ProviderConditionalJS(ProviderConditionalBuilderJS builder)
	{
		super(builder.toMainBuilder());
	}

	public static class ProviderConditionalBuilderJS extends BuilderBase<ProviderConditional>
	{
		private ResourceLocation id;
		private ProviderConditionalType type;
		private Style wieldStyle;
		private Boolean visibleOffHand;
		private Skill skillToCheck;
		private WeaponCategory category;
		private Item weapon;
		private ProviderConditionalJS[] providerConditionals;
		private SkillSlot slot;
		private SkillDataKey<Boolean> key;
		private InteractionHand hand;
		private Predicate<LivingEntityPatch<?>> customFunction;

		public ProviderConditionalBuilderJS(ResourceLocation id)
		{
			super(id);
			type = ProviderConditionalType.DEFAULT;
			skillToCheck = null;
			category = null;
			weapon = null;
			providerConditionals = null;
			slot = null;
			key = null;
			hand = null;
			customFunction = null;
			wieldStyle = null;
			visibleOffHand = false;
		}

		public ProviderConditional.ProviderConditionalBuilder toMainBuilder()
		{
			return ProviderConditional.builder().setProviderConditionals(providerConditionals)
					.setSkillToCheck(skillToCheck)
					.setCategory(category)
					.isVisibleOffHand(visibleOffHand)
					.setCustomFunction(customFunction)
					.setWieldStyle(wieldStyle)
					.setHand(hand)
					.setSlot(slot)
					.setKey(key)
					.setType(type)
					.setWeapon(weapon);
		}

		@Override
		public RegistryInfo getRegistryType()
		{
			return ExCapKubeJSPlugin.PROVIDER_REGISTRY;
		}

		@Override
		public ProviderConditional createObject()
		{
			return build();
		}

		public ProviderConditionalJS build()
		{
			return new ProviderConditionalJS(this);
		}

		public ProviderConditionalBuilderJS isVisibleOffHand(boolean visibleOffHand)
		{
			this.visibleOffHand = visibleOffHand;
			return this;
		}

		public ProviderConditionalBuilderJS setWieldStyle(Style wieldStyle)
		{
			this.wieldStyle = wieldStyle;
			return this;
		}

		public ProviderConditionalBuilderJS setType(@NotNull ProviderConditionalType type)
		{
			this.type = type;
			return this;
		}

		public ProviderConditionalBuilderJS setSkillToCheck(Skill skillToCheck)
		{
			this.skillToCheck = skillToCheck;
			return this;
		}

		public ProviderConditionalBuilderJS setCategory(WeaponCategory category)
		{
			this.category = category;
			return this;
		}

		public ProviderConditionalBuilderJS setWeapon(Item weapon)
		{
			this.weapon = weapon;
			return this;
		}

		public ProviderConditionalBuilderJS setProviderConditionals(ProviderConditionalJS... providerConditionals)
		{
			this.providerConditionals = providerConditionals;
			return this;
		}

		public ProviderConditionalBuilderJS setSlot(SkillSlot slot)
		{
			this.slot = slot;
			return this;
		}

		public ProviderConditionalBuilderJS setKey(SkillDataKey<Boolean> key)
		{
			this.key = key;
			return this;
		}

		public ProviderConditionalBuilderJS setHand(InteractionHand hand)
		{
			this.hand = hand;
			return this;
		}

		public ProviderConditionalBuilderJS setCustomFunction(Predicate<LivingEntityPatch<?>> customFunction)
		{
			this.customFunction = customFunction;
			return this;
		}
	}
}

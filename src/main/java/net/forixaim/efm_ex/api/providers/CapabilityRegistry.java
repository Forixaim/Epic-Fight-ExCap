package net.forixaim.efm_ex.api.providers;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import javax.annotation.Nullable;
import java.util.function.Function;

public class CapabilityRegistry
{
	private final String MOD_ID;
	private CapabilityRegistry(final String modId)
	{
		MOD_ID = modId;
	}

	public static CapabilityRegistry create(final String modId)
	{
		return new CapabilityRegistry(modId);
	}

	//Skill Existence/Activation
	public ProviderConditional add(@NotNull String name, @NotNull ProviderConditionalType type, @NotNull SkillSlot slot, @NotNull Skill skill, @Nullable Style style, @Nullable Boolean combo, Skill weaponPassive)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), type, style, skill, weaponPassive, slot, null, combo);
	}

	//Data Key
	public ProviderConditional add(@NotNull String name, @NotNull SkillSlot slot, @Nullable SkillDataKey<Boolean> key, @Nullable Style style, @Nullable Boolean combo, Skill passive)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.DATA_KEY, style, null, passive, slot, key, combo);
	}

	//Weapon Category
	public ProviderConditional add(@NotNull String name, @NotNull InteractionHand hand, @Nullable WeaponCategory category, Style style, Boolean combo, Skill passive)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.WEAPON_CATEGORY, style, hand, passive, category, null, combo);
	}

	//Specific Weapon
	public ProviderConditional add(@NotNull String name, @NotNull InteractionHand hand, Item item, Style style, Boolean combo, Skill passive)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.SPECIFIC_WEAPON, style, hand, passive, null, item, combo);
	}

	//Composite
	public ProviderConditional add(@NotNull String name, Style style, Boolean combination, Skill passive, ProviderConditional... conditionals)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.COMPOSITE, style, combination, passive, conditionals);
	}

	//Custom
	public ProviderConditional add(@NotNull String name, Style style, Boolean combination, Skill passive, Function<LivingEntityPatch<?>, Boolean> customFunction)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.CUSTOM, style, combination, passive, customFunction);
	}

	//Default
	public ProviderConditional add(@NotNull String name, Style style, Boolean combination, Skill passive)
	{
		return new ProviderConditional(new ResourceLocation(MOD_ID, name), ProviderConditionalType.DEFAULT, style, combination, passive);
	}

	//Add Attack Style
	public Pair<Style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder>> add(Style style, Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> registry)
	{
		return Pair.of(style, registry);
	}
}

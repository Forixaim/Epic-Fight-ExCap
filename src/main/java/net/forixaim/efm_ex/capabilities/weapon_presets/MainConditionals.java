package net.forixaim.efm_ex.capabilities.weapon_presets;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class MainConditionals
{
    public static ProviderConditional default1HWieldStyle = ProviderConditional.builder()
            .setType(ProviderConditionalType.DEFAULT)
            .setWieldStyle(CapabilityItem.Styles.ONE_HAND)
            .isVisibleOffHand(true)
            .build();

    public static ProviderConditional default2HWieldStyle = ProviderConditional.builder()
            .setType(ProviderConditionalType.DEFAULT)
            .isVisibleOffHand(false)
            .setWieldStyle(CapabilityItem.Styles.TWO_HAND)
            .build();

    public static ProviderConditional defaultRanged = ProviderConditional.builder()
            .setType(ProviderConditionalType.DEFAULT)
            .isVisibleOffHand(false)
            .setWieldStyle(CapabilityItem.Styles.RANGED)
            .build();


    public static ProviderConditional SwordShieldLS = ProviderConditional.builder()
            .setType(ProviderConditionalType.WEAPON_CATEGORY)
            .setHand(InteractionHand.OFF_HAND)
            .setCategory(CapabilityItem.WeaponCategories.SHIELD)
            .setWieldStyle(CapabilityItem.Styles.ONE_HAND)
            .isVisibleOffHand(true)
            .build();

    public static ProviderConditional LiechtenauerCondition = ProviderConditional.builder()
            .setType(ProviderConditionalType.SKILL_ACTIVATION)
            .setSlot(SkillSlots.WEAPON_INNATE)
            .setSkillToCheck(EpicFightSkills.LIECHTENAUER)
            .isVisibleOffHand(false)
            .setWieldStyle(CapabilityItem.Styles.OCHS).build();

    public static ProviderConditional UchigatanaSheathed = ProviderConditional.builder()
            .setType(ProviderConditionalType.DATA_KEY)
            .isVisibleOffHand(false)
            .setSlot(SkillSlots.WEAPON_PASSIVE)
            .setSkillToCheck(EpicFightSkills.BATTOJUTSU_PASSIVE)
            .setWieldStyle(CapabilityItem.Styles.SHEATH)
            .setKey(SkillDataKeys.SHEATH.get())
            .build();

    public static ProviderConditional DualDaggers = ProviderConditional.builder()
            .setType(ProviderConditionalType.WEAPON_CATEGORY)
            .setCategory(CapabilityItem.WeaponCategories.DAGGER)
            .isVisibleOffHand(true)
            .setHand(InteractionHand.OFF_HAND)
            .setWieldStyle(CapabilityItem.Styles.TWO_HAND)
            .build();

    public static ProviderConditional DualSwords = ProviderConditional.builder()
            .setType(ProviderConditionalType.WEAPON_CATEGORY)
            .setCategory(CapabilityItem.WeaponCategories.SWORD)
            .isVisibleOffHand(true)
            .setHand(InteractionHand.OFF_HAND)
            .setWieldStyle(CapabilityItem.Styles.TWO_HAND)
            .build();
}

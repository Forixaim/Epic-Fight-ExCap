package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import static net.forixaim.efm_ex.capabilities.weapon_presets.types.MainConditionals.UchigatanaSheathed;

public class UchigatanaType extends CoreCapability {
	private static final UchigatanaType instance = new UchigatanaType();

    public UchigatanaType()
    {
        init();
    }

    public static UchigatanaType getInstance()
    {
        return instance;
    }

    private void init()
    {
        provider.addConditional(DefaultConditionals.default2HWieldStyle);
        provider.addConditional(UchigatanaSheathed);
        builder.initialSetup(
                        CapabilityItem.WeaponCategories.UCHIGATANA,
                        EpicFightSounds.WHOOSH.get(),
                        EpicFightSounds.BLADE_HIT.get()
                ).collider(ColliderPreset.UCHIGATANA)
                .weaponCombinationPredicator(provider.exportCombination())
                .styleProvider(provider.exportStyle());
    }


}

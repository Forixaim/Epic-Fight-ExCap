package net.forixaim.efm_ex.capabilities.weapon_presets.types;

import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

/**
 * Default conditionals used for quick access.
 */
public class DefaultConditionals
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
}

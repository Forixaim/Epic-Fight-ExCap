package net.forixaim.ex_cap.kjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.forixaim.ex_cap.api.moveset.MoveSet;
import net.forixaim.ex_cap.api.providers.ProviderConditional;
import net.forixaim.ex_cap.capabilities.CoreCapability;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.forixaim.ex_cap.kjs.moveset.CustomMoveSet;
import net.forixaim.ex_cap.kjs.moveset.ProviderConditionalJS;

public class ExCapKubeJSPlugin extends KubeJSPlugin
{
    public static final RegistryInfo<MoveSet> MOVESET_REGISTRY = RegistryInfo.of(MoveSet.REGISTRY_KEY, MoveSet.class);
    public static final RegistryInfo<ProviderConditional> PROVIDER_REGISTRY = RegistryInfo.of(ProviderConditional.REGISTRY_KEY, ProviderConditional.class);
    public static final RegistryInfo<CoreCapability> WEAPON_REGISTRY = RegistryInfo.of(ExCapWeapons.REGISTRY_KEY, CoreCapability.class);



    @Override
    public void init()
    {
        MOVESET_REGISTRY.addType("create", CustomMoveSet.CustomMoveSetBuilder.class, CustomMoveSet.CustomMoveSetBuilder::new);
        PROVIDER_REGISTRY.addType("create", ProviderConditionalJS.ProviderConditionalBuilderJS.class, ProviderConditionalJS.ProviderConditionalBuilderJS::new);
        super.init();
    }
}

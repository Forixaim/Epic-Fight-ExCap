package net.forixaim.efm_ex.api.providers;

import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ComboProvider
{
    private final List<ProviderConditional> conditionals;

    public ComboProvider()
    {
        conditionals = new ArrayList<ProviderConditional>();
    }

    public ComboProvider addConditional(ProviderConditional conditional)
    {
        this.conditionals.add(conditional);
        return this;
    }

    public ComboProvider addDefaultConditional(ProviderConditional conditional)
    {
        this.conditionals.add(0, conditional);
        return this;
    }


}

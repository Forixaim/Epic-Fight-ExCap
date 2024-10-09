package net.forixaim.efm_ex.api.providers;

import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * This class is meant to be as an extendbale
 */
public class CoreWeaponCapabilityProvider
{
    private final List<ProviderConditional> conditionals;

    public CoreWeaponCapabilityProvider()
    {
        conditionals = new ArrayList<ProviderConditional>();
    }

    public CoreWeaponCapabilityProvider addConditional(ProviderConditional conditional)
    {
        this.conditionals.add(0, conditional);
        return this;
    }

    public CoreWeaponCapabilityProvider addDefaultConditional(ProviderConditional conditional)
    {
        this.conditionals.add(conditional);
        return this;
    }

    private void sortByPriority()
    {
        for (int i = 0; i < conditionals.size() - 1; i++)
        {
            ProviderConditional conditional = conditionals.get(i).copy();
            if (conditional.getPriority() < conditionals.get(i + 1).getPriority())
            {
                conditionals.set(i, conditionals.get(i+1));
                conditionals.set(i+1, conditional);
            }
        }
    }

    /**
     * @throws NullPointerException if none of the provided Conditionals return a Style;
     * @return The Function that is used for the StyleProvider
     */
    public Function<LivingEntityPatch<?>, Style> exportStyle()
    {
        sortByPriority();
	    return entityPatch ->
        {
            for (ProviderConditional conditional : conditionals)
            {
                if (conditional.testConditionalStyle(entityPatch) != null)
                {
                    return conditional.testConditionalStyle(entityPatch);
                }
            }
            return null;
        };
    }

    /**
     * @throws NullPointerException if none of the provided Conditionals return a Style;
     * @return The Function that is used for the StyleProvider
     */
    public Function<LivingEntityPatch<?>, Boolean> exportCombination()
    {
        sortByPriority();
	    return entityPatch ->
        {
            for (ProviderConditional conditional : conditionals)
            {
                if (conditional.testConditionalCombo(entityPatch) != null)
                {
                    return conditional.testConditionalCombo(entityPatch);
                }
            }
            return null;
        };
    }

    public Function<LivingEntityPatch<?>, Skill> exportWeaponPassiveSkill()
    {
        sortByPriority();
        return entityPatch ->
        {
          for (ProviderConditional conditional : conditionals)
          {
              if (conditional.testConditionalSkill(entityPatch) != null)
              {
                  return conditional.testConditionalSkill(entityPatch);
              }
          }
          return null;
        };
    }
}

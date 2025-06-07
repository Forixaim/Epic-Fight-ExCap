package net.forixaim.efm_ex.api.material;

import yesman.epicfight.api.utils.math.ValueModifier;

public class MaterialProperties
{
    private final float armorNegationModifier;
    private final float impactModifier;
    private final int maxStrikesModifier;

    public MaterialProperties(float armorNegationModifier, float impactModifier, int maxStrikesModifier)
    {
        this.armorNegationModifier = armorNegationModifier;
        this.impactModifier = impactModifier;
        this.maxStrikesModifier = maxStrikesModifier;
    }

    public float getArmorNegationModifier() {
        return armorNegationModifier;
    }

    public float getImpactModifier() {
        return impactModifier;
    }

    public int getMaxStrikesModifier() {
        return maxStrikesModifier;
    }
}

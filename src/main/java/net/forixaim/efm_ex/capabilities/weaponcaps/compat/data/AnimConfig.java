package net.forixaim.efm_ex.capabilities.weaponcaps.compat.data;

public class AnimConfig {
    private final int nAttacks;
    private final AnimType type;

    public AnimConfig(AnimType type, int nAttacks) {
        this.type = type;
        this.nAttacks = nAttacks;
    }

    public int getComboSize() {
        return nAttacks;
    }

    public AnimType getType() {
        return type;
    }
}

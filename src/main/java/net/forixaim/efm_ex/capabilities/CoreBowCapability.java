package net.forixaim.efm_ex.capabilities;

import net.forixaim.efm_ex.capabilities.weaponcaps.EXBowWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXRangedWeaponCapability;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;

import java.util.function.Consumer;

public class CoreBowCapability extends CoreCapability
{
    protected EXWeaponCapability.Builder builder = EXBowWeaponCapability.builder();

    CoreBowCapability start(Consumer<EXBowWeaponCapability.Builder> qs)
    {
        qs.accept(this.builder);
        return this;
    }

}

package net.forixaim.efm_ex.capabilities.weaponcaps;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

public class EXGloveCapability extends EXWeaponCapability
{
    public EXGloveCapability(CapabilityItem.Builder builder)
    {
        super(builder);
    }

    @Override
    public boolean checkOffhandValid(LivingEntityPatch<?> entitypatch) {
        ItemStack offhandItem = entitypatch.getOriginal().getOffhandItem();
        CapabilityItem itemCap = EpicFightCapabilities.getItemStackCapability(offhandItem);
        boolean isFist = itemCap.getWeaponCategory() == WeaponCategories.FIST;
        return isFist || !(offhandItem.getItem() instanceof SwordItem || offhandItem.getItem() instanceof DiggerItem);
    }

    @Override
    public boolean canHoldInOffhandAlone() {
        return true;
    }
}

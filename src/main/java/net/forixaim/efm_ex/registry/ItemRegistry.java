package net.forixaim.efm_ex.registry;

import net.forixaim.efm_ex.EpicFightEXCapability;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EpicFightEXCapability.MODID);
    public static final RegistryObject<Item> IRON_LONGSWORD_SHEATH = ITEMS.register("iron_longsword_sheath", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_LONGSWORD_SHEATH = ITEMS.register("golden_longsword_sheath", () -> new Item(new Item.Properties()));

}

package net.forixaim.ex_cap;

import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.moveset.ExCapWeaponReloadListener;
import net.forixaim.ex_cap.capabilities.ExCapCategories;
import net.forixaim.ex_cap.capabilities.ExCapStyle;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.forixaim.ex_cap.registry.ItemRegistry;
import net.forixaim.ex_cap.skill.ExCapDatakeys;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EpicFightEXCapability.MODID)
public class EpicFightEXCapability {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "efm_ex";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public EpicFightEXCapability(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::addPackFindersEvent);
        ExCapDatakeys.DATA_KEYS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        ExCapWeapons.EX_CAP_WEAPONS.register(modEventBus);
        MinecraftForge.EVENT_BUS.addListener(this::addResourceReloader);
        MinecraftForge.EVENT_BUS.register(this);
        Style.ENUM_MANAGER.registerEnumCls(MODID, ExCapStyle.class);
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, ExCapCategories.class);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(EpicFightEXCapability::onFMLLoadComplete);
    }

    private void addResourceReloader(AddReloadListenerEvent event)
    {
        event.addListener(new ExCapWeaponReloadListener());
    }



    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(EpicFightEXCapability.MODID).getFile().findResource("packs/ex_cap_spears");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(EpicFightEXCapability.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, false);
            Pack.ResourcesSupplier resourcesSupplier = (string) -> pack;
            Pack.Info info = Pack.readPackInfo("ex_cap_spears", resourcesSupplier);

            if (info != null) {
                event.addRepositorySource((source) ->
                        source.accept(Pack.create("ex_cap_spears", Component.translatable("pack.ex_cap_spears.title"), false, resourcesSupplier, info, PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
            }
        }
    }

    public static void onFMLLoadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(Registries::registerMaterials);
    }
}

package net.forixaim.ex_cap;

import com.mojang.logging.LogUtils;
import net.forixaim.ex_cap.api.Registries;
import net.forixaim.ex_cap.api.moveset.ExCapWeaponReloadListener;
import net.forixaim.ex_cap.capabilities.CapabilityRegistry;
import net.forixaim.ex_cap.capabilities.ExCapCategories;
import net.forixaim.ex_cap.capabilities.ExCapStyle;
import net.forixaim.ex_cap.capabilities.WeaponModificationRegistry;
import net.forixaim.ex_cap.capabilities.weapon_presets.CoreMovesets;
import net.forixaim.ex_cap.capabilities.weapon_presets.ExCapWeapons;
import net.forixaim.ex_cap.capabilities.weapon_presets.MainConditionals;
import net.forixaim.ex_cap.registry.ItemRegistry;
import net.forixaim.ex_cap.skill.ExCapDatakeys;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.slf4j.Logger;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.api.event.types.registry.WeaponCapabilityPresetRegistryEvent;
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


    public EpicFightEXCapability(IEventBus bus, ModContainer container) {
        bus.addListener(this::addPackFindersEvent);
        ItemRegistry.ITEMS.register(bus);
        ExCapWeapons.EX_CAP_WEAPONS.register(bus);
        bus.addListener(CoreMovesets::registerMovesets);
        bus.addListener(WeaponModificationRegistry::registerExCap);
        NeoForge.EVENT_BUS.addListener(this::addResourceReloader);
        Style.ENUM_MANAGER.registerEnumCls(MODID, ExCapStyle.class);
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, ExCapCategories.class);
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        bus.addListener(this::onCommonSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent event)
    {
    }

    public static void registerWeaponPresets(WeaponCapabilityPresetRegistryEvent event)
    {
        LogUtils.getLogger().debug("size {}", ExCapWeapons.REGISTRY.size());
        ExCapWeapons.REGISTRY.holders().forEach(holder -> event.getTypeEntry().put(holder.unwrapKey().get().location(), CapabilityRegistry.process(holder)));
    }



    private void addResourceReloader(AddReloadListenerEvent event)
    {
        event.addListener(new ExCapWeaponReloadListener());
    }

    public void addPackFindersEvent(AddPackFindersEvent event) {
//        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
//            Path resourcePath = ModList.get().getModFileById(EpicFightEXCapability.MODID).getFile().findResource("packs/ex_cap_spears");
//            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(EpicFightEXCapability.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, false);
//            Pack.ResourcesSupplier resourcesSupplier = (string) -> pack;
//            Pack.Info info = Pack.readPackInfo("ex_cap_spears", resourcesSupplier);
//
//            if (info != null) {
//                event.addRepositorySource((source) ->
//                        source.accept(Pack.create("ex_cap_spears", Component.translatable("pack.ex_cap_spears.title"), false, resourcesSupplier, info, PackType.CLIENT_RESOURCES, Pack.Position.TOP, false, PackSource.BUILT_IN)));
//            }
//        }
    }
}

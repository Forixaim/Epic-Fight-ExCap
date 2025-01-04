package net.forixaim.efm_ex;

import com.mojang.logging.LogUtils;
import net.forixaim.efm_ex.api.Registries;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EpicFightEXCapability.MODID)
public class EpicFightEXCapability {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "efm_ex";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public EpicFightEXCapability() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(EpicFightEXCapability::onComplete);
    }

    public static void onComplete(FMLCommonSetupEvent event)
    {
        event.enqueueWork(Registries::registerMovesets);
        event.enqueueWork(Registries::registerCapabilities);
    }
}

package net.forixaim.efm_ex.api.moveset;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class MoveSetManager extends SimpleJsonResourceReloadListener
{
    public MoveSetManager(Gson p_10768_, String p_10769_) {
        super(p_10768_, p_10769_);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }

    @Override
    public String getName() {
        return super.getName();
    }
}

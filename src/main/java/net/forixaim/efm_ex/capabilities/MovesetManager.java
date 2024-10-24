package net.forixaim.efm_ex.capabilities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.forixaim.bs_api.BattleArtsAPI;
import net.forixaim.bs_api.events.ProficiencyRegistryEvent;
import net.forixaim.bs_api.proficiencies.Proficiency;
import net.forixaim.bs_api.proficiencies.ProficiencyManager;
import net.forixaim.efm_ex.EpicFightEXCapability;
import net.forixaim.efm_ex.event.MovesetRegistrationEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class MovesetManager extends SimpleJsonResourceReloadListener
{
	public static final ResourceKey<Registry<MoveSet>> MOVESET_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(EpicFightEXCapability.MODID, "movesets"));
	public static final List<MoveSet> RegisteredMovesets = Lists.newArrayList();
	private static final Gson GSON = new GsonBuilder().create();


	public MovesetManager()
	{
		super(GSON, "battle_arts_proficiency_parameters");
	}

	@Override
	protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller)
	{
		LogUtils.getLogger().debug("Nothing here... again.");
	}

	public static void createMovesetRegistry(NewRegistryEvent event) {
		event.create(RegistryBuilder.of(new ResourceLocation(EpicFightEXCapability.MODID, "movesets")).addCallback(MovesetCallbacks.INSTANCE));
	}

	public static void registerProficiencies(RegisterEvent event)
	{
		if (event.getRegistryKey().equals(MOVESET_REGISTRY_KEY))
		{
			final MovesetRegistrationEvent proficiencyRegistryEvent = new MovesetRegistrationEvent();
			ModLoader.get().postEvent(proficiencyRegistryEvent);

			event.register(MOVESET_REGISTRY_KEY, registerHelper ->
			{
				proficiencyRegistryEvent.getAllProficiencies().forEach(
						proficiency ->
						{
							registerHelper.register(proficiency.getIdentifier(), proficiency);
							REGISTERED_PROFICIENCIES.add(proficiency);
						}
				);
			});
		}
	}

	@Override
	public @NotNull String getName()
	{
		return "What?";
	}

	private static class MovesetCallbacks implements IForgeRegistry.BakeCallback<Proficiency>, IForgeRegistry.CreateCallback<Proficiency>, IForgeRegistry.ClearCallback<Proficiency>
	{
		private static final ResourceLocation MOVESETS = new ResourceLocation(BattleArtsAPI.MOD_ID, "movesets");
		public static final MovesetCallbacks INSTANCE = new MovesetCallbacks();
		@Override
		@SuppressWarnings("unchecked")
		public void onBake(IForgeRegistryInternal<Proficiency> iForgeRegistryInternal, RegistryManager registryManager)
		{
			LogUtils.getLogger().debug("Baked you a brownie.");
		}

		@Override
		public void onClear(IForgeRegistryInternal<Proficiency> iForgeRegistryInternal, RegistryManager registryManager)
		{
			iForgeRegistryInternal.getSlaveMap(MOVESETS, Map.class).clear();
		}

		@Override
		public void onCreate(IForgeRegistryInternal<Proficiency> iForgeRegistryInternal, RegistryManager registryManager)
		{
			iForgeRegistryInternal.setSlaveMap(MOVESETS, Maps.newHashMap());
		}
	}
}

package net.forixaim.efm_ex.event;

import com.google.common.collect.Lists;
import net.forixaim.bs_api.events.ProficiencyRegistryEvent;
import net.forixaim.bs_api.proficiencies.Proficiency;
import net.forixaim.efm_ex.capabilities.MoveSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.List;
import java.util.function.Function;

public class MovesetRegistrationEvent extends Event implements IModBusEvent
{
	private final List<ModRegistryWorker> modRegisterWorkers = Lists.newArrayList();

	public static class ModRegistryWorker {
		private final String ModID;
		private final List<MoveSet> modProficiencies = Lists.newArrayList();

		private ModRegistryWorker(String ModID) {
			this.ModID = ModID;
		}

		public <S extends MoveSet, B extends Proficiency.Builder<S>> S build(String name, Function<B, S> constructor, B builder) {
			final ResourceLocation registryName = new ResourceLocation(this.ModID, name);
			builder.setRegistryName(registryName);

			final S skill = constructor.apply(builder);

			this.modProficiencies.add(skill);

			return skill;
		}
	}
}

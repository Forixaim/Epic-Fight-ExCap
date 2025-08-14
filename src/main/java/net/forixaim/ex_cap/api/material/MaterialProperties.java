package net.forixaim.ex_cap.api.material;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.Map;

public record MaterialProperties(Map<Attribute, Double> attributeModifier) {

}

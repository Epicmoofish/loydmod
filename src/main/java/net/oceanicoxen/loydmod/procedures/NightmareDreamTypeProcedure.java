package net.oceanicoxen.loydmod.procedures;

import net.oceanicoxen.loydmod.LoydmodModVariables;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.LoydmodMod;

import net.minecraft.entity.Entity;

import java.util.Map;

@LoydmodModElements.ModElement.Tag
public class NightmareDreamTypeProcedure extends LoydmodModElements.ModElement {
	public NightmareDreamTypeProcedure(LoydmodModElements instance) {
		super(instance, 116);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LoydmodMod.LOGGER.warn("Failed to load dependency entity for procedure NightmareDreamType!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (((entity.getCapability(LoydmodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new LoydmodModVariables.PlayerVariables())).DreamType) == 2);
	}
}

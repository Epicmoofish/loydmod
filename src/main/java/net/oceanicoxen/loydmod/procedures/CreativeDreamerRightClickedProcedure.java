package net.oceanicoxen.loydmod.procedures;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.oceanicoxen.loydmod.LoydmodMod;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.LoydmodModVariables;

@LoydmodModElements.ModElement.Tag
public class CreativeDreamerRightClickedProcedure extends LoydmodModElements.ModElement {
	public CreativeDreamerRightClickedProcedure(LoydmodModElements instance) {
		super(instance, 112);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LoydmodMod.LOGGER.warn("Failed to load dependency entity for procedure CreativeDreamerRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) ((((entity.getCapability(LoydmodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new LoydmodModVariables.PlayerVariables())).DreamType) + 1) % 4);
			if (entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity)entity;
			if (_setval == 0) {
				player.sendMessage(new StringTextComponent(
						"Your dreams will no longer be predetermined")
						.mergeStyle(TextFormatting.WHITE),
				Util.DUMMY_UUID);
			}
			if (_setval == 1) {
				player.sendMessage(new StringTextComponent(
						"Your dreams will be good dreams.")
						.mergeStyle(TextFormatting.AQUA),
				Util.DUMMY_UUID);
			}
			if (_setval == 2) {
				player.sendMessage(new StringTextComponent(
						"Your dreams will be nightmares.")
						.mergeStyle(TextFormatting.DARK_RED),
				Util.DUMMY_UUID);
			}
			if (_setval == 3) {
				player.sendMessage(new StringTextComponent(
						"Your dreams will be lucid.")
						.mergeStyle(TextFormatting.GOLD),
				Util.DUMMY_UUID);
			}
			entity.getCapability(LoydmodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.DreamType = _setval;
				capability.syncPlayerVariables(entity);
			});
			}
		}
	}
}

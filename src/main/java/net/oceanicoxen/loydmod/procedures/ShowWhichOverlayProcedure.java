package net.oceanicoxen.loydmod.procedures;

import net.oceanicoxen.loydmod.item.CreativeDreamerItem;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.LoydmodMod;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@LoydmodModElements.ModElement.Tag
public class ShowWhichOverlayProcedure extends LoydmodModElements.ModElement {
	public ShowWhichOverlayProcedure(LoydmodModElements instance) {
		super(instance, 113);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				LoydmodMod.LOGGER.warn("Failed to load dependency entity for procedure ShowWhichOverlay!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(CreativeDreamerItem.block, (int) (1)).getItem())) {
			return (true);
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(CreativeDreamerItem.block, (int) (1)).getItem())) {
			return (true);
		}
		return (false);
	}
}

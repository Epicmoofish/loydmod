
package net.oceanicoxen.loydmod.block;

import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

@LoydmodModElements.ModElement.Tag
public class DreamPlanksBlock extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dream_planks")
	public static final Block block = null;
	public DreamPlanksBlock(LoydmodModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 3f).setLightLevel((state) -> {
			      return 0;
			   }).harvestLevel(0)
					.harvestTool(ToolType.AXE));
			setRegistryName("dream_planks");
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 5;
		}
	}
}

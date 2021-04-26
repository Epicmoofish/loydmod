
package net.oceanicoxen.loydmod.block;

import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

@LoydmodModElements.ModElement.Tag
public class DreamCobblestoneBlock extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dream_cobblestone")
	public static final Block block = null;
	public DreamCobblestoneBlock(LoydmodModElements instance) {
		super(instance, 40);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f, 6f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("dream_cobblestone");
		}
	}
}

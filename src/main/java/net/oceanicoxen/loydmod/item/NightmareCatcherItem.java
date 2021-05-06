
package net.oceanicoxen.loydmod.item;

import net.oceanicoxen.loydmod.ChanceChanger;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@LoydmodModElements.ModElement.Tag
public class NightmareCatcherItem extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:nightmare_catcher")
	public static final Item block = null;
	public NightmareCatcherItem(LoydmodModElements instance) {
		super(instance, 106);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends ChanceChanger {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON),0.0F,0,50);
			setRegistryName("nightmare_catcher");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}

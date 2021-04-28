
package net.oceanicoxen.loydmod.item;

import net.oceanicoxen.loydmod.procedures.TeleportBackToOverworldProcedure;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Rarity;
import net.minecraft.stats.Stats;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

@LoydmodModElements.ModElement.Tag
public class TinyNeedleItem extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:tiny_needle")
	public static final Item block = null;
	public TinyNeedleItem(LoydmodModElements instance) {
		super(instance, 52);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.TRANSPORTATION).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("tiny_needle");
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

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			RegistryKey<World> destinationType2 = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("loydmod:dream_dimension"));
			if (entity.world.getDimensionKey()==destinationType2) {
			entity.addStat(Stats.ITEM_USED.get(this));
		      if (!entity.abilities.isCreativeMode) {
		         itemstack.shrink(1);
		      }
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				TeleportBackToOverworldProcedure.executeProcedure($_dependencies);
			}
			return ActionResult.func_233538_a_(itemstack, world.isRemote());
		}

	}
}


package net.oceanicoxen.loydmod.block;

import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Random;
import java.util.Collections;

@LoydmodModElements.ModElement.Tag
public class DreamGrassBlockBlock extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dream_grass_block")
	public static final Block block = null;
	public DreamGrassBlockBlock(LoydmodModElements instance) {
		super(instance, 37);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends GrassBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.6f, 0.6f).lightValue(0).harvestLevel(-1)
					.harvestTool(ToolType.SHOVEL).tickRandomly());
			setRegistryName("dream_grass_block");
		}
		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
			return true;
		}

		@Override
		public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		      BlockPos blockpos = pos.up();
		      BlockState blockstate = Blocks.GRASS.getDefaultState();

		      for(int i = 0; i < 128; ++i) {
		         BlockPos blockpos1 = blockpos;
		         int j = 0;

		         while(true) {
		            if (j >= i / 16) {
		               BlockState blockstate2 = worldIn.getBlockState(blockpos1);
		               if (blockstate2.getBlock() == blockstate.getBlock() && rand.nextInt(10) == 0) {
		                  ((IGrowable)blockstate.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
		               }

		               if (!blockstate2.isAir()) {
		                  break;
		               }

		               BlockState blockstate1;
		               if (rand.nextInt(8) == 0) {
		                  List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getFlowers();
		                  if (list.isEmpty()) {
		                     break;
		                  }

		                  ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig)(list.get(0)).config).feature;
		                  blockstate1 = ((FlowersFeature)configuredfeature.feature).getFlowerToPlace(rand, blockpos1, configuredfeature.config);
		               } else {
		                  blockstate1 = blockstate;
		               }

		               if (blockstate1.isValidPosition(worldIn, blockpos1)) {
		                  worldIn.setBlockState(blockpos1, blockstate1, 3);
		               }
		               break;
		            }

		            blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
		            if (worldIn.getBlockState(blockpos1.down()).getBlock() != this || worldIn.getBlockState(blockpos1).isCollisionShapeOpaque(worldIn, blockpos1)) {
		               break;
		            }

		            ++j;
		         }
		      }

		   }

		private static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
		      BlockPos blockpos = p_220257_2_.up();
		      BlockState blockstate = p_220257_1_.getBlockState(blockpos);
		      if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
		         return true;
		      } else {
		         int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP, blockstate.getOpacity(p_220257_1_, blockpos));
		         return i < p_220257_1_.getMaxLightLevel();
		      }
		   }

		   private static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
		      BlockPos blockpos = p_220256_2_.up();
		      return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_) && !p_220256_1_.getFluidState(blockpos).isTagged(FluidTags.WATER);
		   }
		   @Override
		   public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		      if (!func_220257_b(state, worldIn, pos)) {
		         if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
		         worldIn.setBlockState(pos, DreamDirtBlock.block.getDefaultState());
		      } else {
		         if (worldIn.getLight(pos.up()) >= 9) {
		            BlockState blockstate = this.getDefaultState();

		            for(int i = 0; i < 4; ++i) {
		               BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
		               if (worldIn.getBlockState(blockpos).getBlock() == DreamDirtBlock.block && func_220256_c(blockstate, worldIn, blockpos)) {
		                  worldIn.setBlockState(blockpos, blockstate.with(SNOWY, Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
		               }
		            }
		         }

		      }
		   }
	}
}

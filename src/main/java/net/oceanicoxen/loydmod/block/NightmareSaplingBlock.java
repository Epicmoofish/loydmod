
package net.oceanicoxen.loydmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import net.oceanicoxen.loydmod.NightmareTree;
import net.oceanicoxen.loydmod.LoydmodModElements;

@LoydmodModElements.ModElement.Tag
public class NightmareSaplingBlock extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:nightmare_sapling")
	public static final Block block = null;
	public NightmareSaplingBlock(LoydmodModElements instance) {
		super(instance, 24);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class BlockCustomFlower extends BushBlock implements IGrowable {
		   public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
		   protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
		 
		   public BlockCustomFlower() {
				super(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.PLANT)
						.hardnessAndResistance(0f, 0f).setLightLevel((state) -> {
						      return 0;
						   }));
				setRegistryName("nightmare_sapling");
				this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, Integer.valueOf(0)));
			}

			@Override
			public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
				return 100;
			}

			@Override
			public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
				return 60;
			}

		   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		      return SHAPE;
		   }

		   public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		      super.tick(state, worldIn, pos, rand);
		      if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		      if (worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0) {
		         this.func_226942_a_(worldIn, pos, state, rand);
		      }

		   }

		   public void func_226942_a_(ServerWorld p_226942_1_, BlockPos p_226942_2_, BlockState p_226942_3_, Random p_226942_4_) {
		      if (p_226942_3_.get(STAGE) == 0) {
		         p_226942_1_.setBlockState(p_226942_2_, p_226942_3_.func_235896_a_(STAGE), 4);
		      } else {
		         if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(p_226942_1_, p_226942_4_, p_226942_2_)) return;
		         new NightmareTree().attemptGrowTree(p_226942_1_, p_226942_1_.getChunkProvider().getChunkGenerator(), p_226942_2_, p_226942_3_, p_226942_4_);
		      }

		   }

		   /**
		    * Whether this IGrowable can grow
		    */
		   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		      return true;
		   }

		   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		      return (double)worldIn.rand.nextFloat() < 0.45D;
		   }

		   public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		      this.func_226942_a_(worldIn, pos, state, rand);
		   }

		   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		      builder.add(STAGE);
		   }
}
}

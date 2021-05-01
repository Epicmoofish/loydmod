/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class LoydmodModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.oceanicoxen.loydmod as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.oceanicoxen.loydmod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.Property;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.oceanicoxen.loydmod.block.DreamCobblestoneBlock;
import net.oceanicoxen.loydmod.block.DreamDirtBlock;
import net.oceanicoxen.loydmod.block.DreamGrassBlockBlock;
import net.oceanicoxen.loydmod.block.DreamLeavesBlock;
import net.oceanicoxen.loydmod.block.DreamLogBlock;
import net.oceanicoxen.loydmod.block.DreamPlanksBlock;
import net.oceanicoxen.loydmod.block.DreamStoneBlock;

@LoydmodModElements.ModElement.Tag
public class DreamyExplosion extends Explosion {
	/**
	 * Do not remove this constructor
	 */
	private final boolean causesFire;
	   private final Explosion.Mode mode;
	   private final Random random = new Random();
	   private final World world;
	   private final double x;
	   private final double y;
	   private final double z;
	   @Nullable
	   private final Entity exploder;
	   private final float size;
	   private DamageSource damageSource;
	   private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
	   private final Map<PlayerEntity, Vector3d> playerKnockbackMap = Maps.newHashMap();
	   private final Vector3d position;
	@OnlyIn(Dist.CLIENT)
	   public DreamyExplosion(World worldIn, @Nullable Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions) {
	      this(worldIn, entityIn, x, y, z, size, false, Explosion.Mode.DESTROY, affectedPositions);
	   }

	   @OnlyIn(Dist.CLIENT)
	   public DreamyExplosion(World worldIn, @Nullable Entity exploderIn, double xIn, double yIn, double zIn, float sizeIn, boolean causesFireIn, Explosion.Mode modeIn, List<BlockPos> affectedBlockPositionsIn) {
	      this(worldIn, exploderIn, xIn, yIn, zIn, sizeIn, causesFireIn, modeIn);
	      this.affectedBlockPositions.addAll(affectedBlockPositionsIn);
	   }

	   public DreamyExplosion(World worldIn, @Nullable Entity exploderIn, double xIn, double yIn, double zIn, float sizeIn, boolean causesFireIn, Explosion.Mode modeIn) {
		   super(worldIn, exploderIn, xIn, yIn, zIn, sizeIn, false, modeIn);
		   this.world = worldIn;
	      this.exploder = exploderIn;
	      this.size = sizeIn;
	      this.x = xIn;
	      this.y = yIn;
	      this.z = zIn;
	      this.causesFire = causesFireIn;
	      this.mode = modeIn;
	      this.damageSource = DamageSource.causeExplosionDamage(this);
	      this.position = new Vector3d(this.x, this.y, this.z);
	   }
	   List<Block> normalBlocks=Arrays.asList(Blocks.OAK_LOG,Blocks.COBBLESTONE,Blocks.DIRT,Blocks.GRASS_BLOCK,Blocks.OAK_PLANKS,Blocks.STONE,Blocks.OAK_LEAVES);
	   List<Block> dreamBlocks=Arrays.asList(DreamLogBlock.block,DreamCobblestoneBlock.block,DreamDirtBlock.block,DreamGrassBlockBlock.block,DreamPlanksBlock.block,DreamStoneBlock.block,DreamLeavesBlock.block);
	   public <T extends Comparable<T>, V extends T> BlockState replaceBlock(BlockState state) {
		   for (int a =0; a<dreamBlocks.size();a++) {
			   if (dreamBlocks.get(a)==state.getBlock()) {
				   BlockState newState=normalBlocks.get(a).getDefaultState();
				   for (Object b:state.getProperties().toArray()) {
					   Property<T> prop =  (Property<T>)b;
					   newState=newState.with(prop, state.get(prop));
				   }
				   return newState;
			   }
			   if (normalBlocks.get(a)==state.getBlock()) {
				   BlockState newState=dreamBlocks.get(a).getDefaultState();
				   for (Object b:state.getProperties().toArray()) {
					   Property<T> prop =  (Property<T>)b;
					   newState=newState.with(prop, state.get(prop));
				   }
				   return newState;
			   }
		   }
		   return state;
	   }
	   public void doExplosionA() {
		      Set<BlockPos> set = Sets.newHashSet();
		      int i = 16;

//		      for(int j = 0; j < 16; ++j) {
//		         for(int k = 0; k < 16; ++k) {
//		            for(int l = 0; l < 16; ++l) {
//		               if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
//		                  double d0 = (double)((float)j / 15.0F * 2.0F - 1.0F);
//		                  double d1 = (double)((float)k / 15.0F * 2.0F - 1.0F);
//		                  double d2 = (double)((float)l / 15.0F * 2.0F - 1.0F);
//		                  double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
//		                  d0 = d0 / d3;
//		                  d1 = d1 / d3;
//		                  d2 = d2 / d3;
//		                  float f = this.size * (0.7F + this.world.rand.nextFloat() * 0.6F);
//		                  double d4 = this.x;
//		                  double d6 = this.y;
//		                  double d8 = this.z;
////		                  for(float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
////		                     BlockPos blockpos = new BlockPos(d4, d6, d8);
////		                     BlockState blockstate = this.world.getBlockState(blockpos);
////		                     FluidState ifluidstate = this.world.getFluidState(blockpos);
////		                     if (!blockstate.isAir(this.world, blockpos) || !ifluidstate.isEmpty()) {
////		                        float f2 = Math.max(blockstate.getExplosionResistance(this.world, blockpos, this), ifluidstate.getExplosionResistance(this.world, blockpos, this));
////		                        if (this.exploder != null) {
////		                           f2 = this.exploder.getExplosionResistance(this, this.world, blockpos, blockstate, ifluidstate, f2);
////		                        }
////
////		                        f -= (f2 + 0.3F) * 0.3F;
////		                     }
////
////		                     if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, this.world, blockpos, blockstate, f))) {
////		                        set.add(blockpos);
////		                     }
////
////		                     d4 += d0 * (double)0.3F;
////		                     d6 += d1 * (double)0.3F;
////		                     d8 += d2 * (double)0.3F;
////		                  }
//		               }
//		            }
//		         }
//		      }
		      for (int dx=(int) (-this.size);dx<this.size+1;dx++) {
		    	  for (int dy=(int) (-this.size);dy<this.size+1;dy++) {
		    		  for (int dz=(int) (-this.size);dz<this.size+1;dz++) {
		            	  if (dx*dx+dy*dy+dz*dz<=this.size*this.size) {
		            		  set.add(new BlockPos((int)this.x+dx,(int)this.y+dy,(int)this.z+dz));
		            	  }
		              }
	              }
              }

		      this.affectedBlockPositions.addAll(set);
		      float f3 = this.size * 2.0F;
		      int k1 = MathHelper.floor(this.x - (double)f3 - 1.0D);
		      int l1 = MathHelper.floor(this.x + (double)f3 + 1.0D);
		      int i2 = MathHelper.floor(this.y - (double)f3 - 1.0D);
		      int i1 = MathHelper.floor(this.y + (double)f3 + 1.0D);
		      int j2 = MathHelper.floor(this.z - (double)f3 - 1.0D);
		      int j1 = MathHelper.floor(this.z + (double)f3 + 1.0D);
		      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
		      net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
		      Vector3d vec3d = new Vector3d(this.x, this.y, this.z);

		      for(int k2 = 0; k2 < list.size(); ++k2) {
		         Entity entity = list.get(k2);
		         if (!entity.isImmuneToExplosions()) {
		            double d12 = (double)(MathHelper.sqrt(entity.getDistanceSq(vec3d)) / f3);
		            if (d12 <= 1.0D) {
		               double d5 = entity.getPosX() - this.x;
		               double d7 = entity.getPosYEye() - this.y;
		               double d9 = entity.getPosZ() - this.z;
		               double d13 = (double)MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
		               if (d13 != 0.0D) {
		                  d5 = d5 / d13;
		                  d7 = d7 / d13;
		                  d9 = d9 / d13;
		                  double d14 = (double)getBlockDensity(vec3d, entity);
		                  double d10 = (1.0D - d12) * d14;
		                  if (entity instanceof PlayerEntity) {
		                     PlayerEntity playerentity = (PlayerEntity)entity;
		                     if (!playerentity.isSpectator() && (!playerentity.isCreative() || !playerentity.abilities.isFlying)) {
		                        this.playerKnockbackMap.put(playerentity, new Vector3d(d5 * d10, d7 * d10, d9 * d10));
		                     }
		                  }
		               }
		            }
		         }
		      }

		   }

		   /**
		    * Does the second part of the explosion (sound, particles, drop spawn)
		    */
		   public void doExplosionB(boolean spawnParticles) {
		      if (this.world.isRemote) {
		         this.world.playSound(this.x, this.y, this.z, SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
		      }

		      boolean flag = this.mode != Explosion.Mode.NONE;
		      if (spawnParticles) {
		         if (!(this.size < 2.0F) && flag) {
		            this.world.addParticle(ParticleTypes.FIREWORK, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		         } else {
		            this.world.addParticle(ParticleTypes.FIREWORK, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
		         }
		      }

		      if (flag) {
		         ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList<>();
		         Collections.shuffle(this.affectedBlockPositions, this.world.rand);

		         for(BlockPos blockpos : this.affectedBlockPositions) {
		            BlockState blockstate = this.world.getBlockState(blockpos);
		            Block block = blockstate.getBlock();
		            if (!blockstate.isAir(this.world, blockpos)) {
		               BlockPos blockpos1 = blockpos.toImmutable();
		               this.world.getProfiler().startSection("explosion_blocks");
		               if (blockstate.canDropFromExplosion(this.world, blockpos, this) && this.world instanceof ServerWorld) {
		                  TileEntity tileentity = blockstate.hasTileEntity() ? this.world.getTileEntity(blockpos) : null;
		                  
		               }

		               world.setBlockState(blockpos,replaceBlock(blockstate));
		               this.world.getProfiler().endSection();
		            }
		         }

		         for(Pair<ItemStack, BlockPos> pair : objectarraylist) {
		            Block.spawnAsEntity(this.world, pair.getSecond(), pair.getFirst());
		         }
		      }

		      if (this.causesFire) {
		         for(BlockPos blockpos2 : this.affectedBlockPositions) {
		            if (this.random.nextInt(3) == 0 && this.world.getBlockState(blockpos2).isAir() && this.world.getBlockState(blockpos2.down()).isOpaqueCube(this.world, blockpos2.down())) {
		               this.world.setBlockState(blockpos2, Blocks.FIRE.getDefaultState());
		            }
		         }
		      }

		   }
}

package net.oceanicoxen.loydmod;


import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.oceanicoxen.loydmod.block.DreamLeavesBlock;
import net.oceanicoxen.loydmod.block.DreamLogBlock;
import net.oceanicoxen.loydmod.block.DreamSaplingBlock;

public class DreamTree extends Tree {
   /**
    * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
    */
	public static final TreeFeatureConfig DREAM_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable)DreamSaplingBlock.block).build();
	public static final TreeFeatureConfig FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new BlobFoliagePlacer(0, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling((net.minecraftforge.common.IPlantable)DreamSaplingBlock.block).build();
	public static final TreeFeatureConfig DREAM_TREE_WITH_MORE_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling((net.minecraftforge.common.IPlantable)DreamSaplingBlock.block).build();
	public static final TreeFeatureConfig FANCY_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)DreamSaplingBlock.block).build();
	@Nullable
	   protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
	      return randomIn.nextInt(10) == 0 ? Feature.FANCY_TREE.withConfiguration(p_225546_2_ ? FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG : FANCY_TREE_CONFIG) : Feature.NORMAL_TREE.withConfiguration(p_225546_2_ ? DREAM_TREE_WITH_MORE_BEEHIVES_CONFIG : DREAM_TREE_CONFIG);
	   }
}
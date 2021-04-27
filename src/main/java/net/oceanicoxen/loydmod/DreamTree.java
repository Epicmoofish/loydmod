package net.oceanicoxen.loydmod;


import java.util.OptionalInt;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.oceanicoxen.loydmod.block.DreamLeavesBlock;
import net.oceanicoxen.loydmod.block.DreamLogBlock;

public class DreamTree extends Tree {
   /**
    * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
    */
	public static final BaseTreeFeatureConfig DREAM_TREE_CONFIG = new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1)).setIgnoreVines().build();
	public static final BaseTreeFeatureConfig FANCY_TREE_CONFIG = new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(DreamLogBlock.block.getDefaultState()), new SimpleBlockStateProvider(DreamLeavesBlock.block.getDefaultState()), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
	
	public static final BaseTreeFeatureConfig FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG = FANCY_TREE_CONFIG.func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT));
					public static final BaseTreeFeatureConfig DREAM_TREE_WITH_MORE_BEEHIVES_CONFIG = DREAM_TREE_CONFIG.func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT));
						@Nullable
	   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
	      return randomIn.nextInt(10) == 0 ? Feature.TREE.withConfiguration(p_225546_2_ ? FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG : FANCY_TREE_CONFIG) : Feature.TREE.withConfiguration(p_225546_2_ ? DREAM_TREE_WITH_MORE_BEEHIVES_CONFIG : DREAM_TREE_CONFIG);
	   }
}
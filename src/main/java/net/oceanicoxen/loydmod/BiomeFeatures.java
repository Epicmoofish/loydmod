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

import com.google.common.collect.ImmutableList;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class BiomeFeatures {
	/**
	 * Do not remove this constructor
	 */
	static ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_BEE_HIVE = Feature.TREE.withConfiguration(DreamTree.FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG);
    static ConfiguredFeature<BaseTreeFeatureConfig, ?> NORMAL_BEE_HIVE = Feature.TREE.withConfiguration(DreamTree.DREAM_TREE_WITH_MORE_BEEHIVES_CONFIG);
     static ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY = Feature.TREE.withConfiguration(DreamTree.FANCY_TREE_CONFIG);
    static ConfiguredFeature<BaseTreeFeatureConfig, ?> NORMAL = Feature.TREE.withConfiguration(DreamTree.DREAM_TREE_CONFIG);
    static ConfiguredFeature<BaseTreeFeatureConfig, ?> NI_FANCY_BEE_HIVE = Feature.TREE.withConfiguration(NightmareTree.FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG);
    static ConfiguredFeature<BaseTreeFeatureConfig, ?> NI_NORMAL_BEE_HIVE = Feature.TREE.withConfiguration(NightmareTree.NIGHTMARE_TREE_WITH_MORE_BEEHIVES_CONFIG);
     static ConfiguredFeature<BaseTreeFeatureConfig, ?> NI_FANCY = Feature.TREE.withConfiguration(NightmareTree.FANCY_TREE_CONFIG);
    static ConfiguredFeature<BaseTreeFeatureConfig, ?> NI_NORMAL = Feature.TREE.withConfiguration(NightmareTree.NIGHTMARE_TREE_CONFIG);

	public static ConfiguredFeature<?, ?> configuredFeat=register("loydmod:dream_tree_scarce",Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(FANCY_BEE_HIVE.withChance(0.33333334F)), NORMAL_BEE_HIVE)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));	      
	public static ConfiguredFeature<?, ?> configuredFeatUn=register("loydmod:dream_tree",Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(FANCY.withChance(0.33333334F)), NORMAL)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(16, 1F, 0))));	      
	public static ConfiguredFeature<?, ?> configuredFeatNi=register("loydmod:nightmare_tree_scarce",Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(NI_FANCY_BEE_HIVE.withChance(0.33333334F)), NI_NORMAL_BEE_HIVE)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));	      
	public static ConfiguredFeature<?, ?> configuredFeatUnNi=register("loydmod:nightmare_tree",Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(NI_FANCY.withChance(0.33333334F)), NI_NORMAL)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(16, 1F, 0))));	      
	public static void withDreamTreesScarce(BiomeGenerationSettings.Builder builder) {
		builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BiomeFeatures.configuredFeat);
	}
	public static void withDreamTrees(BiomeGenerationSettings.Builder builder) {
		builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BiomeFeatures.configuredFeatUn);
	}
	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
	      return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	   }
	public static void withNightmareTrees(BiomeGenerationSettings.Builder builder) {
		builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BiomeFeatures.configuredFeatUnNi);
	}
	public static void withNightmareTreesScarce(BiomeGenerationSettings.Builder builder) {
		builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BiomeFeatures.configuredFeatNi);
	}
}

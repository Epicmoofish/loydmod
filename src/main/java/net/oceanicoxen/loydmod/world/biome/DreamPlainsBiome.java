
package net.oceanicoxen.loydmod.world.biome;

import com.google.common.collect.ImmutableList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import net.oceanicoxen.loydmod.BiomeFeatures;
import net.oceanicoxen.loydmod.DreamTree;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.block.DreamDirtBlock;
import net.oceanicoxen.loydmod.block.DreamGrassBlockBlock;

@LoydmodModElements.ModElement.Tag
public class DreamPlainsBiome extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dream_plains")
	public static Biome biome;
	public DreamPlainsBiome(LoydmodModElements instance) {
		super(instance, 28);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}


	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	private static class BiomeRegisterHandler {
		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(-6684673).setWaterColor(-1).setWaterFogColor(-3342337)
						.withSkyColor(-1).withFoliageColor(-10027009).withGrassColor(-16724788).build();
				 BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(
							SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(DreamGrassBlockBlock.block.getDefaultState(),
									 DreamDirtBlock.block.getDefaultState(), DreamDirtBlock.block.getDefaultState())));
				 
//			      biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(FANCY_BEE_HIVE.withChance(0.33333334F)), NORMAL_BEE_HIVE)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.05F, 1))));
			     BiomeFeatures.withDreamTreesScarce(biomegenerationsettings$builder); 
				 DefaultBiomeFeatures.withNormalGrassPatch(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withDefaultFlowers(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);

			      DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withDisks(biomegenerationsettings$builder);

			      DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);

			      MobSpawnInfo.Builder mobspawninfo$builder = (new MobSpawnInfo.Builder());
			      biome=(new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Category.NONE).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).setEffects((new BiomeAmbience.Builder()).setFogColor(-6684673).setWaterColor(-1).setWaterFogColor(-3342337)
							.withSkyColor(-1).withFoliageColor(-10027009).withGrassColor(-16724788).build()).withMobSpawnSettings(mobspawninfo$builder.copy()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
			   
			event.getRegistry().register(biome.setRegistryName("loydmod:dream_plains"));
		}
	}
}
}

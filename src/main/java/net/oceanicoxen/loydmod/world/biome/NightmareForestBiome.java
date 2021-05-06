
package net.oceanicoxen.loydmod.world.biome;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
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
public class NightmareForestBiome extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:nightmare_forest")
	public static Biome biome;
	public NightmareForestBiome(LoydmodModElements instance) {
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
			      BiomeFeatures.withNightmareTrees(biomegenerationsettings$builder); 
			      DefaultBiomeFeatures.withAllForestFlowerGeneration(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

			      DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withDefaultFlowers(biomegenerationsettings$builder);
			         DefaultBiomeFeatures.withForestGrass(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);
			      MobSpawnInfo.Builder mobspawninfo$builder = (new MobSpawnInfo.Builder());
			      biome=(new Biome.Builder()).precipitation(Biome.RainType.RAIN).depth(0.125F).category(Category.NONE).scale(0.05F).temperature(0.8F).downfall(0.4F).setEffects((new BiomeAmbience.Builder()).setFogColor(-6684673).setWaterColor(-1).setWaterFogColor(-3342337)
							.withSkyColor(-1).withFoliageColor(-10027009).withGrassColor(-10027009).build()).withMobSpawnSettings(mobspawninfo$builder.copy()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
			      
			event.getRegistry().register(biome.setRegistryName("loydmod:nightmare_forest"));
		}
	}
}
}

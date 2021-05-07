
package net.oceanicoxen.loydmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import net.oceanicoxen.loydmod.BiomeFeatures;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.block.NightmareDirtBlock;
import net.oceanicoxen.loydmod.block.NightmareGrassBlockBlock;

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
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(0xb51b04).setWaterColor(0x961805).setWaterFogColor(0xb51b04)
						.withSkyColor(-1).withFoliageColor(0xb51b04).withGrassColor(0xb51b04).build();
				 BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(
							SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(NightmareGrassBlockBlock.block.getDefaultState(),
									 NightmareDirtBlock.block.getDefaultState(), NightmareDirtBlock.block.getDefaultState())));
			      BiomeFeatures.withNightmareTrees(biomegenerationsettings$builder); 
			      DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

			      DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withDefaultFlowers(biomegenerationsettings$builder);
			         DefaultBiomeFeatures.withForestGrass(biomegenerationsettings$builder);
			      DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);
			      MobSpawnInfo.Builder mobspawninfo$builder = (new MobSpawnInfo.Builder());
			      biome=(new Biome.Builder()).precipitation(Biome.RainType.RAIN).depth(0.125F).category(Category.NONE).scale(0.05F).temperature(0.8F).downfall(0.4F).setEffects(effects).withMobSpawnSettings(mobspawninfo$builder.copy()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
			      
			event.getRegistry().register(biome.setRegistryName("loydmod:nightmare_forest"));
		}
	}
}
}

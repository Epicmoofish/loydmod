
package net.oceanicoxen.loydmod.world.dimension;

import net.oceanicoxen.loydmod.block.DreamStoneBlock;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.block.Block;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;

import com.google.common.collect.ImmutableSet;

@LoydmodModElements.ModElement.Tag
public class DreamDimensionDimension extends LoydmodModElements.ModElement {
	public DreamDimensionDimension(LoydmodModElements instance) {
		super(instance, 29);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			try {
				ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CAVE, new ImmutableSet.Builder<Block>()
						.addAll((Set<Block>) ObfuscationReflectionHelper.getPrivateValue(WorldCarver.class, WorldCarver.CAVE, "field_222718_j"))
						.add(DreamStoneBlock.block.getDefaultState().getBlock()).build(), "field_222718_j");
				ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CANYON, new ImmutableSet.Builder<Block>()
						.addAll((Set<Block>) ObfuscationReflectionHelper.getPrivateValue(WorldCarver.class, WorldCarver.CANYON, "field_222718_j"))
						.add(DreamStoneBlock.block.getDefaultState().getBlock()).build(), "field_222718_j");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		DimensionRenderInfo customEffect = new DimensionRenderInfo(128, true, DimensionRenderInfo.FogType.NONE, false, false) {
			private final float[] field_239209_b_ = new float[4];
			@Override
			public Vector3d func_230494_a_(Vector3d color, float sunHeight) {
				return new Vector3d(0.752941176471, 0.847058823529, 1);
			}

			@Override
			public boolean func_230493_a_(int x, int y) {
				return false;
			}

			@Override
			public float[] func_230492_a_(float p_230492_1_, float p_230492_2_) {
				// TODO Auto-generated method stub
				float f = 0.4F;
			      float f1 = MathHelper.cos(1/2 * ((float)Math.PI * 2F)) - 0.0F;
			      float f2 = -0.0F;
			      if (f1 >= -0.4F && f1 <= 0.4F) {
			         float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
			         float f4 = 1.0F - (1.0F - MathHelper.sin(f3 * (float)Math.PI)) * 0.99F;
			         f4 = f4 * f4;
			         this.field_239209_b_[0] = f3 * 0.3F + 0.7F;
			         this.field_239209_b_[1] = f3 * f3 * 0.7F + 0.2F;
			         this.field_239209_b_[2] = f3 * f3 * 0.0F + 0.2F;
			         this.field_239209_b_[3] = f4;
			         return this.field_239209_b_;
			      } else {
			         return null;
			      }
			}
			
			
		};
		DeferredWorkQueue.runLater(() -> {
			try {
				Object2ObjectMap<ResourceLocation, DimensionRenderInfo> effectsRegistry = (Object2ObjectMap<ResourceLocation, DimensionRenderInfo>) ObfuscationReflectionHelper
						.getPrivateValue(DimensionRenderInfo.class, null, "field_239208_a_");
				effectsRegistry.put(new ResourceLocation("loydmod:dream_dimension"), customEffect);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

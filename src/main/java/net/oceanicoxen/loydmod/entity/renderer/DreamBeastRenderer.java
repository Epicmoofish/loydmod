package net.oceanicoxen.loydmod.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.oceanicoxen.loydmod.entity.DreamBeastEntity;

@OnlyIn(Dist.CLIENT)
public class DreamBeastRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(DreamBeastEntity.entity, renderManager -> {
				MobRenderer renderer = new MobRenderer(renderManager, new ModelDreamBotModel(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("loydmod:textures/dream_bot.png");
					}
				};
			
			renderer.addLayer(new GlowingLayer<>(renderer));
				return renderer;
			});
		}
	}
	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("loydmod:textures/dream_bot_glowing.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}
	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.15 - 1.16
	// Paste this class into your mod and generate all required imports
	public static class ModelDreamBotModel extends EntityModel<Entity> {
		private final ModelRenderer dreamBeastHead;
		private final ModelRenderer dreamBeastBody;
		private final ModelRenderer dreamBeastRightArm;
		private final ModelRenderer dreamBeastLeftArm;
		private final ModelRenderer dreamBeastRightLeg;
		private final ModelRenderer dreamBeastLeftLeg;
		public ModelDreamBotModel() {
			textureWidth = 128;
			textureHeight = 128;
			dreamBeastHead = new ModelRenderer(this);
			dreamBeastHead.setRotationPoint(-0.5F, 9.5F, -3.5F);
			dreamBeastHead.setTextureOffset(52, 100).addBox(-2.5F, -4.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
			dreamBeastBody = new ModelRenderer(this);
			dreamBeastBody.setRotationPoint(0.0F, -7.0F, 0.0F);
			dreamBeastBody.setTextureOffset(78, 93).addBox(-7.0F, 17.0F, -8.0F, 13.0F, 7.0F, 10.0F, 0.0F, false);
			dreamBeastRightArm = new ModelRenderer(this);
			dreamBeastRightArm.setRotationPoint(6.0F, 12.5F, -2.0F);
			dreamBeastRightArm.setTextureOffset(45, 114).addBox(0.0F, -0.5F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
			dreamBeastLeftArm = new ModelRenderer(this);
			dreamBeastLeftArm.setRotationPoint(-7.0F, 12.5F, -2.0F);
			dreamBeastLeftArm.setTextureOffset(58, 114).addBox(-2.0F, -0.5F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
			dreamBeastRightLeg = new ModelRenderer(this);
			dreamBeastRightLeg.setRotationPoint(4.0F, 17.0F, -2.0F);
			dreamBeastRightLeg.setTextureOffset(70, 118).addBox(-1.0F, 5.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
			dreamBeastRightLeg.setTextureOffset(88, 118).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			dreamBeastLeftLeg = new ModelRenderer(this);
			dreamBeastLeftLeg.setRotationPoint(-5.0F, 17.0F, -2.0F);
			dreamBeastLeftLeg.setTextureOffset(111, 118).addBox(-1.0F, 5.0F, -4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
			dreamBeastLeftLeg.setTextureOffset(100, 118).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			dreamBeastHead.render(matrixStack, buffer, packedLight, packedOverlay);
			dreamBeastBody.render(matrixStack, buffer, packedLight, packedOverlay);
			dreamBeastRightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			dreamBeastLeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			dreamBeastRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			dreamBeastLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.dreamBeastLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.dreamBeastLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.dreamBeastHead.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.dreamBeastHead.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.dreamBeastRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.dreamBeastRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}
}

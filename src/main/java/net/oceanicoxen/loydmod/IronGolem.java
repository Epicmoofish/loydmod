// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


public class IronGolem extends EntityModel<Entity> {
	private final ModelRenderer ironGolemHead;
	private final ModelRenderer ironGolemBody;
	private final ModelRenderer ironGolemRightArm;
	private final ModelRenderer ironGolemLeftArm;
	private final ModelRenderer ironGolemLeftLeg;
	private final ModelRenderer ironGolemRightLeg;
	private final ModelRenderer getArmHoldingRose;

	public IronGolem() {
		textureWidth = 128;
		textureHeight = 128;

		ironGolemHead = new ModelRenderer(this);
		ironGolemHead.setRotationPoint(0.0F, -7.0F, -2.0F);
		ironGolemHead.setTextureOffset(0, 0).addBox(-8.0F, -10.0F, 0.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
		ironGolemHead.setTextureOffset(24, 1).addBox(-5.0F, -2.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		ironGolemBody = new ModelRenderer(this);
		ironGolemBody.setRotationPoint(0.0F, -7.0F, 0.0F);
		ironGolemBody.setTextureOffset(0, 41).addBox(-14.0F, 0.0F, -2.0F, 20.0F, 12.0F, 10.0F, 0.0F, false);

		ironGolemRightArm = new ModelRenderer(this);
		ironGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		

		ironGolemLeftArm = new ModelRenderer(this);
		ironGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		

		ironGolemLeftLeg = new ModelRenderer(this);
		ironGolemLeftLeg.setRotationPoint(-4.0F, 11.0F, 0.0F);
		

		ironGolemRightLeg = new ModelRenderer(this);
		ironGolemRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		getArmHoldingRose = new ModelRenderer(this);
		getArmHoldingRose.setRotationPoint(0.0F, 0.0F, 0.0F);
		
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		ironGolemHead.render(matrixStack, buffer, packedLight, packedOverlay);
		ironGolemBody.render(matrixStack, buffer, packedLight, packedOverlay);
		ironGolemRightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		ironGolemLeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		ironGolemLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		ironGolemRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		getArmHoldingRose.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
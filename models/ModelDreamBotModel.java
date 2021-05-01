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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.dreamBeastLeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.dreamBeastLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.dreamBeastHead.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.dreamBeastHead.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.dreamBeastRightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.dreamBeastRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
	}
}
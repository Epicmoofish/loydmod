// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelNightmare extends EntityModel<Entity> {
	private final ModelRenderer Body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer Head;

	public ModelNightmare() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-4.0F, -16.0F, -4.0F, 7.0F, 9.0F, 7.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -25.0F, -3.0F, 5.0F, 9.0F, 5.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.5672F, 0.0F, -0.48F);
		cube_r1.setTextureOffset(0, 0).addBox(1.0493F, 0.1398F, 1.9825F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.5672F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-1.7953F, -0.119F, 2.1474F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3927F, 0.0F, -0.5672F);
		cube_r3.setTextureOffset(0, 0).addBox(3.3798F, 0.1737F, -2.2192F, 1.0F, 4.0F, 3.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.4363F, 0.0F, 0.5672F);
		cube_r4.setTextureOffset(0, 0).addBox(0.8089F, -2.5897F, -0.9924F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.3927F, 0.0F, 0.5672F);
		cube_r5.setTextureOffset(0, 0).addBox(-3.1911F, 0.0531F, -2.4685F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.5672F);
		cube_r6.setTextureOffset(0, 0).addBox(-1.1911F, -1.0911F, -0.2565F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		cube_r6.setTextureOffset(0, 0).addBox(-3.1911F, -0.0911F, -0.2565F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(-1.2047F, -6.414F, 0.2565F);
		Body.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.5672F, 0.0F, 0.2618F);
		cube_r7.setTextureOffset(0, 0).addBox(-1.7769F, 0.5355F, -4.5245F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(2.0F, 3.0F, 1.0F);
		RightArm.setTextureOffset(0, 0).addBox(0.0F, -2.0F, -8.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
		RightArm.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -10.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		RightArm.setTextureOffset(0, 0).addBox(2.0F, -1.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		RightArm.setTextureOffset(0, 0).addBox(0.0F, -2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(-3.0F, 3.0F, 1.0F);
		LeftArm.setTextureOffset(0, 0).addBox(-3.0F, -2.0F, -8.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
		LeftArm.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -10.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		LeftArm.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftArm.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.5F, -1.0F, -0.5F);
		Head.setTextureOffset(4, 33).addBox(-7.5F, -11.0F, -7.5F, 15.0F, 15.0F, 15.0F, -4.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}
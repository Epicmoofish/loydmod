
package net.oceanicoxen.loydmod.gui.overlay;

import net.oceanicoxen.loydmod.procedures.ShowWhichOverlayProcedure;
import net.oceanicoxen.loydmod.procedures.NoDreamTypeProcedure;
import net.oceanicoxen.loydmod.procedures.NightmareDreamTypeProcedure;
import net.oceanicoxen.loydmod.procedures.LucidDreamTypeProcedure;
import net.oceanicoxen.loydmod.procedures.GoodDreamTypeProcedure;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

import com.google.common.collect.ImmutableMap;

@LoydmodModElements.ModElement.Tag
public class WhichDreamOverlayOverlay extends LoydmodModElements.ModElement {
	public WhichDreamOverlayOverlay(LoydmodModElements instance) {
		super(instance, 113);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int posX = (event.getWindow().getScaledWidth()) / 2;
			int posY = (event.getWindow().getScaledHeight()) / 2;
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableAlphaTest();
			if (ShowWhichOverlayProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
				if (LucidDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("loydmod:textures/creative_dreamer.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 97 - 18, posY + -119, 0, 0, 16, 16, 16, 16);
				}
				if (LucidDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
					Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "Dream Type: Lucid Dream", posX + 97, posY + -115,
							-3355648);
				if (NightmareDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
					Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "Dream Type: Nightmare", posX + 107, posY + -115,
							-6750208);
				if (NoDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
					Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "No dream type set", posX + 126, posY + -115, -1);
				if (NoDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("loydmod:textures/creative_dreamer.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 108, posY + -119, 0, 0, 16, 16, 16, 16);
				}
				if (GoodDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity)))
					Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "Dream Type: Good Dream", posX + 103, posY + -115,
							-16711681);
				if (GoodDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("loydmod:textures/creative_dreamer.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 103 - 18, posY + -119, 0, 0, 16, 16, 16, 16);
				}
				if (NightmareDreamTypeProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("loydmod:textures/creative_dreamer.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + 107 - 18, posY + -119, 0, 0, 16, 16, 16, 16);
				}
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}


package net.oceanicoxen.loydmod.potion;

import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;

@LoydmodModElements.ModElement.Tag
public class DreamEnhancedPotion extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dream_enhanced")
	public static final Effect potion = null;
	public DreamEnhancedPotion(LoydmodModElements instance) {
		super(instance, 132);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom().addAttributesModifier(Attributes.ATTACK_DAMAGE, "32653B89-116E-49DC-9B6B-9971489B5BE5", 50.0D, AttributeModifier.Operation.ADDITION));
		event.getRegistry().register(new EffectCustom().addAttributesModifier(Attributes.MOVEMENT_SPEED, "34653B89-116E-49DC-9B6B-9971489B5BE5", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL));
		
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.BENEFICIAL, -1);
			setRegistryName("dream_enhanced");
			potionIcon = new ResourceLocation("loydmod:textures/dreamgem.png");
		}

		@Override
		public String getName() {
			return "effect.dream_enhanced";
		}

		@Override
		public boolean isBeneficial() {
			return true;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}

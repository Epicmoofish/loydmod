
package net.oceanicoxen.loydmod.potion;

import net.oceanicoxen.loydmod.procedures.DreamyOnPotionActiveTickProcedure;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.HashMap;

@LoydmodModElements.ModElement.Tag
public class DreamyPotion extends LoydmodModElements.ModElement {
	@ObjectHolder("loydmod:dreamy")
	public static final Effect potion = null;
	@ObjectHolder("loydmod:dreamy")
	public static final Potion potionType = null;
	@ObjectHolder("loydmod:dreamy_long")
	public static final Potion potionTypeLong = null;
	public DreamyPotion(LoydmodModElements instance) {
		super(instance, 34);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
		event.getRegistry().register(new LongPotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(potion, 3600));
			setRegistryName("dreamy");
		}
	}

	public static class LongPotionCustom extends Potion {
		public LongPotionCustom() {
			super(new EffectInstance(potion, 7200));
			setRegistryName("dreamy_long");
		}
	}

	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.BENEFICIAL, -6684673);
			setRegistryName("dreamy");
			potionIcon = new ResourceLocation("loydmod:textures/dreamgem.png");
		}

		@Override
		public String getName() {
			return "effect.dreamy";
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
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DreamyOnPotionActiveTickProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}

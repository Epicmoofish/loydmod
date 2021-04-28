package net.oceanicoxen.loydmod.procedures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.oceanicoxen.loydmod.LoydmodModElements;
import net.oceanicoxen.loydmod.item.TinyNeedleItem;

@LoydmodModElements.ModElement.Tag
public class WakeFromBedProcedureProcedure extends LoydmodModElements.ModElement {
	public WakeFromBedProcedureProcedure(LoydmodModElements instance) {
		super(instance, 49);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WakeFromBedProcedure!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WakeFromBedProcedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		boolean IsAllowedIn = false;
		IsAllowedIn = (boolean) (true);
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((!((itemstackiterator).getItem() == (ItemStack.EMPTY).getItem())) && (!((itemstackiterator).getItem() == TinyNeedleItem.block))) {
						IsAllowedIn = (boolean) (false);
					}
				}
			}
		}
		if ((IsAllowedIn)) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
					if (((ServerPlayerEntity) _ent).getSleepTimer()>40) {
						((ServerPlayerEntity) _ent).wakeUp();
						RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("loydmod:dream_dimension"));
						ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) _ent, true, "field_184851_cj");
					ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket());
					((ServerPlayerEntity) _ent).teleport(nextWorld, _ent.getPosition().getX(), _ent.getPosition().getY(),
							_ent.getPosition().getZ(), _ent.rotationYaw, _ent.rotationPitch);
					for(double d0 = _ent.getPosY(); d0 > 0.0D && d0 < _ent.world.getHeight(); ++d0) {
						((ServerPlayerEntity) _ent).teleport(nextWorld,_ent.getPosX(), d0, _ent.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
			            if (_ent.world.hasNoCollisions(_ent)) {
			               break;
			            }
			         }
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
					for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
						((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
					}
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
				}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerInBed(SleepingTimeCheckEvent event) {
		PlayerEntity entity = event.getPlayer();
		int i = event.getSleepingLocation().get().getX();
		int j = event.getSleepingLocation().get().getY();
		int k = event.getSleepingLocation().get().getZ();
		World world = entity.world;
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}

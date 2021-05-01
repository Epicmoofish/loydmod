package net.oceanicoxen.loydmod.procedures;

import net.oceanicoxen.loydmod.item.TinyNeedleItem;
import net.oceanicoxen.loydmod.item.DreamHolderItem;
import net.oceanicoxen.loydmod.LoydmodModElements;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Util;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.potion.EffectInstance;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import java.util.function.Predicate;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

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
					if ((!((itemstackiterator).getItem() == (ItemStack.EMPTY).getItem()))
							&& (!((itemstackiterator).getItem() == TinyNeedleItem.block))) {
						IsAllowedIn = (boolean) (false);
					}
				}
			}
		}
		if ((IsAllowedIn)) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
					if (((ServerPlayerEntity) _ent).getSleepTimer() > 40) {
						Predicate<Entity> filter = ent -> {
							if (ent instanceof ItemFrameEntity) {
								ItemFrameEntity itemframe = (ItemFrameEntity) ent;
								return itemframe.getDisplayedItem().getItem() == DreamHolderItem.block;
							}
							return false;
						};
						AxisAlignedBB aabb = new AxisAlignedBB(_ent.getPosition().add(-5, -5, -5), _ent.getPosition().add(5, 5, 5));
						// itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						// null).ifPresent(capability -> {
						// this.internal = capability;
						// this.bound = true;
						// });
						List<Entity> itemframes = _ent.world.getEntitiesWithinAABB(ItemFrameEntity.class, aabb, filter);
						for (int a = 0; a < itemframes.size(); a++) {
							Entity entit = itemframes.get(a);
							if (entit instanceof ItemFrameEntity) {
								ItemFrameEntity itemframe = (ItemFrameEntity) entit;
								ItemStack itemstack = itemframe.getDisplayedItem();
								itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									for (int b = 0; b < 5; b++) {
										ItemStack stack = capability.getStackInSlot(b);
										ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) _ent;
										boolean flag = serverplayerentity.inventory.addItemStackToInventory(stack);
										if (flag && stack.isEmpty()) {
											stack.setCount(1);
											ItemEntity itementity1 = serverplayerentity.dropItem(stack, false);
											if (itementity1 != null) {
												itementity1.makeFakeItem();
											}
											serverplayerentity.world.playSound((PlayerEntity) null, serverplayerentity.getPosX(),
													serverplayerentity.getPosY(), serverplayerentity.getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP,
													SoundCategory.PLAYERS, 0.2F,
													((serverplayerentity.getRNG().nextFloat() - serverplayerentity.getRNG().nextFloat()) * 0.7F
															+ 1.0F) * 2.0F);
											serverplayerentity.container.detectAndSendChanges();
										} else {
											ItemEntity itementity = serverplayerentity.dropItem(stack, false);
											if (itementity != null) {
												itementity.setNoPickupDelay();
												itementity.setOwnerId(serverplayerentity.getUniqueID());
											}
										}
										stack.shrink(64);
										// ((ServerPlayerEntity) _ent).drop
									}
								});
							}
						}
						// for (int a = 0; a<)
						((ServerPlayerEntity) _ent).wakeUp();
						RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
								new ResourceLocation("loydmod:dream_dimension"));
						ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) _ent, true, "field_184851_cj");
						ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
						((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket());
						((ServerPlayerEntity) _ent).teleport(nextWorld, _ent.getPosition().getX(), _ent.getPosition().getY(),
								_ent.getPosition().getZ(), _ent.rotationYaw, _ent.rotationPitch);
						for (double d0 = _ent.getPosY(); d0 > 0.0D && d0 < _ent.world.getHeight(); ++d0) {
							((ServerPlayerEntity) _ent).teleport(nextWorld, _ent.getPosX(), d0, _ent.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
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
		} else {
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
				if (((ServerPlayerEntity) _ent).getSleepTimer() == 40) {
				((ServerPlayerEntity) _ent).sendMessage(new StringTextComponent(
						"You are unable to dream because is weighing you down. Only tiny needles are light enough to be transfered into dreams with you.")
								.mergeStyle(TextFormatting.WHITE),
						Util.DUMMY_UUID);
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

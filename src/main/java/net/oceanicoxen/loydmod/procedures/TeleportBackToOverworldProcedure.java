package net.oceanicoxen.loydmod.procedures;

import java.util.Map;
import java.util.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.oceanicoxen.loydmod.LoydmodModElements;

@LoydmodModElements.ModElement.Tag
public class TeleportBackToOverworldProcedure extends LoydmodModElements.ModElement {
	public TeleportBackToOverworldProcedure(LoydmodModElements instance) {
		super(instance, 52);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure TeleportBackToOverworld!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		RegistryKey<World> gooddream = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("loydmod:dream_dimension"));
		RegistryKey<World> nightmare = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("loydmod:nightmare_dimension"));
		
		if (entity.world.getDimensionKey()==gooddream || entity.world.getDimensionKey()==nightmare) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
					RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("minecraft:overworld"));
					ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) _ent, true, "field_184851_cj");
					ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket());
					ServerPlayerEntity playerIn = ((ServerPlayerEntity) _ent);
					BlockPos blockpos = playerIn.func_241140_K_();
					((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
							nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
					boolean flag = true;
					if (blockpos != null) {
				         Optional<Vector3d> optional = PlayerEntity.func_242374_a(playerIn.server.getWorld(nextWorld.getDimensionKey()), blockpos, playerIn.func_242109_L(), flag,true);
				         if (optional.isPresent()) {
				            Vector3d vec3d = optional.get();
				            playerIn.teleport(nextWorld, vec3d.x, vec3d.y, vec3d.z, _ent.rotationYaw, _ent.rotationPitch);
				         } else {
				            playerIn.connection.sendPacket(new SChangeGameStatePacket());
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

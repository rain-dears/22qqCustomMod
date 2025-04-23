package me.twotwoqq.cmod.mixin;

import me.twotwoqq.cmod.Main;
import me.twotwoqq.cmod.config.ConfigManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld world;

    @Inject(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"))
    public void updateCounter(EntityStatusS2CPacket packet, CallbackInfo ci) {
        Entity entity = packet.getEntity(world);
        if (entity instanceof OtherClientPlayerEntity player && ConfigManager.popCounterEnabled) {
            Main.playerPops.putIfAbsent(player.getUuid(), 0);
            Main.playerPops.compute(player.getUuid(), ((uuid, integer) -> integer + 1));
        }
    }
}

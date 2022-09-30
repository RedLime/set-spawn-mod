package net.set.spawn.mod.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.set.spawn.mod.SetSpawn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "method_12827", at = @At("TAIL"))
    public void onPlayerConnect(ClientConnection clientConnection, ServerPlayerEntity serverPlayerEntity, CallbackInfo ci) {
        if (SetSpawn.shouldSendErrorMessage) {
            Text message = new LiteralText("§c" + SetSpawn.errorMessage + " This run is not verifiable.");
            serverPlayerEntity.sendMessage(message, false);
        }
        SetSpawn.shouldSendErrorMessage = false;
    }

}

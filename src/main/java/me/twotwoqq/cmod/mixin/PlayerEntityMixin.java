package me.twotwoqq.cmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.twotwoqq.cmod.Main;
import me.twotwoqq.cmod.utils.EntityUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text modifyDisplayName(Text original) {
        PlayerEntity player = (PlayerEntity) (Object) this; // Cast mixin instance

        MutableText text = original.copy();

        if (Main.playerpopsToggle) {
            int pops = Main.playerPops.getOrDefault(player.getUuid(), 0);
            text.append(Text.literal(" §b| " + pops + " pops"));
        }

        if (Main.playerpingToggle) {
            text.append(Text.literal(" §b| " + EntityUtils.getPing(player) + " ms"));
        }

        return text;
    }
}
package me.twotwoqq.cmod.mixin;

import me.twotwoqq.cmod.Main;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.twotwoqq.cmod.Main.MC;

@Mixin(EndCrystalItem.class)
public class EndCrystalItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void onUse(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (MC.player == null || MC.world == null) return;

        ItemStack mainHandStack = MC.player.getMainHandStack();
        if (Main.nbToggle) {
            if (mainHandStack.isOf(Items.END_CRYSTAL)) {
                Vec3d e = MC.player.getEyePos();
                BlockHitResult blockHit = MC.world.raycast(new RaycastContext(e, e.add(getClientLookVec().multiply(4.5)),
                        RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, MC.player));

                if (isBlock(Blocks.OBSIDIAN, blockHit.getBlockPos()) || isBlock(Blocks.BEDROCK, blockHit.getBlockPos())) {
                    HitResult hitResult = MC.crosshairTarget;
                    if (hitResult instanceof BlockHitResult blockHit2) {
                        BlockPos pos = blockHit2.getBlockPos();

                        if (canPlaceCrystalServer(pos)) {
                            context.getStack().decrement(-1);
                            cir.setReturnValue(ActionResult.SUCCESS); // Prevents default action
                        }
                    }
                }
            }
        }
    }

    private Vec3d getClientLookVec() {
        return MC.player.getRotationVec(1.0F);
    }

    private boolean isBlock(net.minecraft.block.Block block, BlockPos pos) {
        return MC.world.getBlockState(pos).isOf(block);
    }

    private boolean canPlaceCrystalServer(BlockPos pos) {
        // You need to implement the logic for this check based on your server logic.
        return true;
    }
}

package net.sprunkisnt.pvptweaks.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.sprunkisnt.pvptweaks.PvPTweaks;
import net.sprunkisnt.pvptweaks.event.PlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * Mixin Notes:<br>
     * - For the method inject location: <a href="https://docs.oracle.com/javase/specs/jvms/se14/html/jvms-4.html#jvms-4.3.2">JavaDocs</a>
     * - Commenting out an inject (like below) practically disables it, as now it is just and unused class method
     * - Adding 'locals = LocalCapture.CAPTURE_FAILHARD' to the inject allows for internal function variables of the
     * target function to be used by the function below.
     * - Adding @Shadow would make a variable from the target class visible to this mixin class
     */

     // @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("Head"))
    public void dropItem0(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        PlayerEntity player = (PlayerEntity)(Object) this;
        
        PvPTweaks.LOGGER.warn("Player '" + player.getDisplayName().getString() + "' threw object");
    }

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(PlayerEvents.isInvulnerable) {
            // returns as type of CallbackInfoReturnable for the target function, despite the local function being void
            cir.setReturnValue(false);
        }
    }
}

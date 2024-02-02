package net.sprunkisnt.pvptweaks.mixin;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity {
    private boolean isInvincible = false;
    private final int INVINCE_DUR;
    private int invinceTicks = 0;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world, int invince_dur) {
        super(entityType, world);
        this.INVINCE_DUR = invince_dur;

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (invinceTicks == INVINCE_DUR) {
                this.isInvincible = false;
                this.invinceTicks = 0;
            } else {
                this.invinceTicks++;
            }
        });
    }

    public void setInvincible() {
        this.isInvincible = true;
        this.invinceTicks = 0;
    }

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void onApplyDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (this.isInvincible) {
            ci.cancel();
        }
    }

    public Iterable<ItemStack> getArmorItems() {
        return null;
    }
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return null;
    }
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }
    public Arm getMainArm() {
        return null;
    }
}

package net.sprunkisnt.pvptweaks.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.sprunkisnt.pvptweaks.PvPTweaksUtil;
import net.sprunkisnt.pvptweaks.mixin.PlayerEntityMixin;

public class PlayerEvents {
    public static final float DASH_VEL = 1.0f; // ~2.5 blocks
    private static final PlayerEntityMixin playerEntity = new PlayerEntityMixin(
            EntityType.PLAYER,
            MinecraftClient.getInstance().world,
            2000
    );

    public static void dash(MinecraftClient client) {
        assert client.player != null;
        Vec3d direction = PvPTweaksUtil.getPlayerDirection(client);
        // Increase the player's velocity in the direction of input
        client.player.addVelocity(
                direction.x * DASH_VEL,
                0,
                direction.z * DASH_VEL
        );
        playerEntity.setInvincible();
    }
}

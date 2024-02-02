package net.sprunkisnt.pvptweaks.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import net.sprunkisnt.pvptweaks.PvPTweaksUtil;

public class PlayerEvents {
    public static final float DASH_VEL = 1.0f; // ~2.5 blocks
    public static final int INVULNERABILITY_TICKS = 20;

    public static void dash(MinecraftClient client) {
        assert client.player != null;
        Vec3d direction = PvPTweaksUtil.getPlayerDirection(client);
        // Increase the player's velocity in the direction of input
        client.player.addVelocity(
                direction.x * DASH_VEL,
                0,
                direction.z * DASH_VEL
        );
        client.player.setInvulnerable(true);
    }
}

package net.sprunkisnt.pvptweaks.event;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.sprunkisnt.pvptweaks.PvPTweaks;
import net.sprunkisnt.pvptweaks.PvPTweaksUtil;

public class PlayerEvents {
    //  ===============Player variables ===============
    public static boolean isInvulnerable = false;
    // =============== Dash action variables ===============
    // general
    private static boolean canDash = true;
    public static final float DASH_VEL = 1.0f; // ~2.5 blocks
    // invulnerability
    private static int dashInvTickCount = 0;
    private static final int DASH_INV_TICK_LENGTH = 15; // ~0.75 seconds
    // use delay
    private static int dashDelayTickCount = 0;
    private static final int DASH_DELAY_TICK_LENGTH = 140; // ~7.0 seconds

    public static void attemptDash(MinecraftClient client) {
        if (canDash) {
            assert client.player != null;
            Vec3d direction = PvPTweaksUtil.getPlayerDirection(client);
            // Increase the player's velocity in the direction of input
            client.player.addVelocity(
                    direction.x * DASH_VEL,
                    0,
                    direction.z * DASH_VEL
            );
            isInvulnerable = true;
            dashDelayTickCount = DASH_DELAY_TICK_LENGTH;
        }
    }

    public static void iframeTickDuration(MinecraftServer server) {
        if (isInvulnerable) {
            dashInvTickCount++;
            if (dashInvTickCount >= DASH_INV_TICK_LENGTH) {
                isInvulnerable = false;
                dashInvTickCount = 0;
            }
        }
    }

    public static void dashUseTickDelay(MinecraftServer server) {
        // While the delay is happening (the amount of ticks specified has not yet passed)
        if (dashDelayTickCount > 0) {
            canDash = false;
            dashDelayTickCount--;
        } else { // The delay is over
            canDash = true;
        }
    }
}

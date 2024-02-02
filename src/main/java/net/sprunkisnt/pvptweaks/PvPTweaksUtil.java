package net.sprunkisnt.pvptweaks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class PvPTweaksUtil {
    public static Vec3d getPlayerDirection(MinecraftClient client) {
        int forward = client.options.forwardKey.isPressed() ? 1 : 0;
        int backward = client.options.backKey.isPressed() ? -1 : 0;
        int left = client.options.leftKey.isPressed() ? 1 : 0;
        int right = client.options.rightKey.isPressed() ? -1 : 0;

        double dirX = left + right;
        double dirZ = forward + backward;

        assert client.player != null;
        float yaw = (float) Math.toRadians(client.player.getYaw());
        double localDirX = dirX * Math.cos(yaw) - dirZ * Math.sin(yaw);
        double localDirZ = dirZ * Math.cos(yaw) + dirX * Math.sin(yaw);

        return new Vec3d(localDirX, 0, localDirZ);
    }
}

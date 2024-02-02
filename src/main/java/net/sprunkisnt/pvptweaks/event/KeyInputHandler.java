package net.sprunkisnt.pvptweaks.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.Vec3d;
import net.sprunkisnt.pvptweaks.PvPTweaksUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_PVPTWEAKS = "key.category.pvptweaks.keybinds";
    public static final KeyBinding KEY_PARRY = new KeyBinding(
            "key.pvptweaks.dash",
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY_PVPTWEAKS
    );
    public static final float PARRY_VEL = 0.75f; // ~1.5 blocks

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (KEY_PARRY.wasPressed()) {
                assert client.player != null;
                Vec3d direction = PvPTweaksUtil.getPlayerDirection(client);
                // Increase the player's velocity in the direction of input
                client.player.addVelocity(direction.x * PARRY_VEL, 0, direction.z * PARRY_VEL);
            }
        });
    }

    public static void registerKeybinds() {
        KeyBindingHelper.registerKeyBinding(KEY_PARRY);

        registerKeyInputs();
    }
}
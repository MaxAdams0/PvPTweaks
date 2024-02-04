package net.sprunkisnt.pvptweaks.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.sprunkisnt.pvptweaks.mixin.PlayerEntityMixin;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    protected static PlayerEntityMixin playerMixin;

    public static final String KEY_CATEGORY_PVPTWEAKS = "key.category.pvptweaks.keybinds";
    public static final KeyBinding KEY_DASH = new KeyBinding(
            "key.pvptweaks.dash",
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY_PVPTWEAKS
    );

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (KEY_DASH.wasPressed()) {
                PlayerEvents.attemptDash(client);
            }
        });
    }

    public static void registerKeybinds() {
        KeyBindingHelper.registerKeyBinding(KEY_DASH);

        registerKeyInputs();
    }
}
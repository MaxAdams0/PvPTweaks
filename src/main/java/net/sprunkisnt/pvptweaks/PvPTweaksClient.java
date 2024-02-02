package net.sprunkisnt.pvptweaks;

import net.fabricmc.api.ClientModInitializer;
import net.sprunkisnt.pvptweaks.event.KeyInputHandler;

public class PvPTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.registerKeybinds();
    }
}

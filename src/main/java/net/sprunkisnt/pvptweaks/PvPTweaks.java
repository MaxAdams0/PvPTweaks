package net.sprunkisnt.pvptweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.sprunkisnt.pvptweaks.event.PlayerEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PvPTweaks implements ModInitializer {
	public static final String MOD_ID = "pvptweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_ID);
		ServerTickEvents.END_SERVER_TICK.register(PlayerEvents::iframeTickDuration);
		ServerTickEvents.END_SERVER_TICK.register(PlayerEvents::dashUseTickDelay);
	}
}
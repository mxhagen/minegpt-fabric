package net.m_hgn.minegpt;

import net.fabricmc.api.ClientModInitializer;
import net.m_hgn.minegpt.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MineGptClient implements ClientModInitializer {
    public static final String MOD_ID = "minegpt3";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Registering Commands...");
        ModRegistries.registerCommands();
    }
}

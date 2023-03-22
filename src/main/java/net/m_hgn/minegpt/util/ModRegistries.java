package net.m_hgn.minegpt.util;

import net.m_hgn.minegpt.command.GptCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ModRegistries {
    public static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(GptCommand::register);
    }
}

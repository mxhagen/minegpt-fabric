package net.m_hgn.minegpt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.m_hgn.minegpt.api.Gpt3Api;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.m_hgn.minegpt.api.Gpt3Api.queryAPI;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class GptCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess access) {
        dispatcher.register(literal("gpt")
                .then(argument("query", greedyString())
                        .executes(GptCommand::run)));

        dispatcher.register(literal("gpt")
                .then(literal("api_key")
                        .then(argument("api_key", greedyString())
                                .executes(GptCommand::setApiKey))));
    }

    public static int run(CommandContext<FabricClientCommandSource> context) {
        String query = getString(context, "query");
        Text header = Text.literal("[MineGPT 1.0]\n")
                .styled(style -> style.withColor(TextColor.parse("DARK_GREEN")).withBold(true));

        context.getSource().getPlayer().sendMessage(header);
        context.getSource().getPlayer().sendMessage(Text.literal("Q: " + query + "\n\nGenerating response...")
                .styled(style -> style.withColor(TextColor.fromRgb(0x55ff55))));

        new Thread(() -> {
            try {
                String answer = queryAPI(query);
                context.getSource().getPlayer().sendMessage(Text.literal(answer)
                        .styled(style -> style.withColor(TextColor.fromRgb(0x55ff55)))
                );
            } catch (Exception e) {
                e.printStackTrace();

                context.getSource().getPlayer()
                        .sendMessage(Text.literal("\nMineGPT: Error: '" + e.getMessage() + "'"
                                        + "\n\n This may happen if you haven't provided an API Key first using"
                                        + "\n`/gpt api_key <key>`")
                                .styled(style -> style.withColor(TextColor.fromRgb(0xff5555)).withItalic(true)));
            }
        }).start();

        return 1;
    }

    private static int setApiKey(CommandContext<FabricClientCommandSource> context) {
        String apiKey = StringArgumentType.getString(context, "api_key");
        Gpt3Api.API_KEY = apiKey;
        context.getSource().getPlayer()
                .sendMessage(Text.literal("\nUpdated API Key.")
                        .styled(style -> style.withColor(TextColor.fromRgb(0x55ff55)).withItalic(true)));
        return 1;
    }
}

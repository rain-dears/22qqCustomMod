package me.twotwoqq.cmod;

import me.twotwoqq.cmod.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import java.util.HashMap;
import java.util.UUID;

public class Main implements ModInitializer {

    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final HashMap<UUID, Integer> playerPops = new HashMap<>();

    @Override
    public void onInitialize() {
        // Load configurations
        ConfigManager.load();

        // Registering a client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                client.player.playerScreenHandler.sendContentUpdates();
            }
        });
    }

    // Replace player name with the configured name protect replacement
    public static String replaceName(String string) {
        if (string != null && ConfigManager.nameProtectEnabled) {
            String playerName = MC.getSession().getUsername();
            return string.replaceAll("\\b" + playerName + "\\b", ConfigManager.nameProtectReplacement);
        }
        return string;
    }
}
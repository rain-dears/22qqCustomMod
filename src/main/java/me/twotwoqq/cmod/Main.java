package me.twotwoqq.cmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.UUID;

public class Main implements ModInitializer {

    public static HashMap<UUID, Integer> playerPops = new HashMap<>();
    public static boolean playerpopsToggle = true;
    public static boolean playerpingToggle = true;
    public static boolean nhcToggle = true;
    public static boolean nvToggle = true;
    public static MinecraftClient MC = MinecraftClient.getInstance();

    private static final KeyBinding[] keyBindings = {
            createKeyBinding("Toggle PopCounter", GLFW.GLFW_KEY_F7),
            createKeyBinding("Toggle Ping", GLFW.GLFW_KEY_F6),
            createKeyBinding("Reset PopCounter", GLFW.GLFW_KEY_F8),
            createKeyBinding("Toggle NoHurtCam", GLFW.GLFW_KEY_8),
            createKeyBinding("Toggle Name Visibility", GLFW.GLFW_KEY_9),
    };

    private static final String[] messages = {
            "Toggled PopCounter to ",
            "Toggled Ping to ",
            "PopCounter has been reset!",
            "NoHurtCam is now ",
            "Toggled Name Visibility to ",
    };

    private static final Runnable[] actions = {
            () -> playerpopsToggle = !playerpopsToggle,
            () -> playerpingToggle = !playerpingToggle,
            () -> playerPops.clear(),
            () -> nhcToggle = !nhcToggle,
            () -> nvToggle = !nvToggle,
    };

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            for (int i = 0; i < keyBindings.length; i++) {
                if (keyBindings[i].wasPressed()) {
                    actions[i].run();
                    client.player.sendMessage(Text.of("§9[22qq's Custom Mod] §r" + messages[i] + (i == 2 ? "" : "§e" + getToggleState(i))), false);
                }
            }
        });
    }

    private static KeyBinding createKeyBinding(String name, int key) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(name, key, "22qq Mod"));
    }

    private static boolean getToggleState(int index) {
        return switch (index) {
            case 0 -> playerpopsToggle;
            case 1 -> playerpingToggle;
            case 3 -> nhcToggle;
            case 4 -> nvToggle;
            default -> false;
        };
    }
}
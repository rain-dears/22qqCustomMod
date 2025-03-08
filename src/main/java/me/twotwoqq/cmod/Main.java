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
    public static HashMap<UUID, Integer> playerPing = new HashMap<>();
    public static boolean playerpopsToggle = true;
    public static boolean playerpingToggle = true;
    public static boolean nhcToggle = true;
    public static boolean nvToggle = true;
    public static boolean coToggle = true;
    public static MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {

        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle PopCounter",
                GLFW.GLFW_KEY_F7,
                "22qq Mod"));

        KeyBinding pingtoggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Ping",
                GLFW.GLFW_KEY_F6,
                "22qq Mod"));

        KeyBinding reset = KeyBindingHelper.registerKeyBinding(new KeyBinding("Reset PopCounter",
                GLFW.GLFW_KEY_F8,
                "22qq Mod"));
        KeyBinding nhc = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle NoHurtCam",
                GLFW.GLFW_KEY_8,
                "22qq Mod"));
        KeyBinding nv = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Name Visibility",
                GLFW.GLFW_KEY_9,
                "22qq Mod"));
        KeyBinding co = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Crystal Oprimizer",
                GLFW.GLFW_KEY_0,
                "22qq Mod"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggle.wasPressed()) {
                playerpopsToggle = !playerpopsToggle;
                MC.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rToggled PopCounter to §e" + playerpopsToggle), false);
            } else if (reset.wasPressed()) {
                playerPops.clear();
                MC.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rPopCounter has been reset!"), false);
            } else if (nhc.wasPressed()) {
                nhcToggle = !nhcToggle;
                client.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rNoHurtCam is now §e" + nhcToggle), false);
            } else if (pingtoggle.wasPressed()) {
                playerpingToggle = !playerpingToggle;
                client.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rToggled Ping to §e" + playerpingToggle), false);
            } else if (nv.wasPressed()) {
                nvToggle = !nvToggle;
                client.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rToggled Name Visibility to §e" + nvToggle), false);
            } else if (co.wasPressed()) {
                coToggle = !coToggle;
                client.player.sendMessage(Text.of("§9[22qq's Custom Mod] §rToggled Crystal Optimizer to §e" + coToggle), false);
            }
        });

    }
}

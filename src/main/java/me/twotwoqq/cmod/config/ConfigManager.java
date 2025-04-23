package me.twotwoqq.cmod.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigManager {
    private static final Gson GSON = new Gson();
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("22qqmod.config.json").toFile();

    public static boolean popCounterEnabled = true;
    public static boolean pingEnabled = true;
    public static boolean nameVisibilityEnabled = true;
    public static boolean nameProtectEnabled = false;
    public static String nameProtectReplacement = "22qq";

    public static void save() {
        JsonObject json = new JsonObject();
        json.addProperty("popCounterEnabled", popCounterEnabled);
        json.addProperty("pingEnabled", pingEnabled);
        json.addProperty("nameVisibilityEnabled", nameVisibilityEnabled);
        json.addProperty("nameProtectEnabled", nameProtectEnabled);
        json.addProperty("nameProtectReplacement", nameProtectReplacement);

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(GSON.toJson(json));
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (Scanner reader = new Scanner(CONFIG_FILE)) {
            StringBuilder json = new StringBuilder();
            while (reader.hasNextLine()) {
                json.append(reader.nextLine());
            }

            JsonObject obj = JsonParser.parseString(json.toString()).getAsJsonObject();

            if (obj.has("popCounterEnabled")) popCounterEnabled = obj.get("popCounterEnabled").getAsBoolean();
            if (obj.has("pingEnabled")) pingEnabled = obj.get("pingEnabled").getAsBoolean();
            if (obj.has("nameVisibilityEnabled")) nameVisibilityEnabled = obj.get("nameVisibilityEnabled").getAsBoolean();
            if (obj.has("nameProtectEnabled")) nameProtectEnabled = obj.get("nameProtectEnabled").getAsBoolean();
            if (obj.has("nameProtectReplacement")) nameProtectReplacement = obj.get("nameProtectReplacement").getAsString();

        } catch (IOException e) {
            System.err.println("Failed to load config: " + e.getMessage());
        }
    }
}
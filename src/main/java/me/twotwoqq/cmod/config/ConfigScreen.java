package me.twotwoqq.cmod.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.string.StringController;
import net.minecraft.text.Text;

public class ConfigScreen implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigCategory.Builder toggles = ConfigCategory.createBuilder()
                    .name(Text.literal("22qq Mod Settings"));

            toggles.option(Option.<Boolean>createBuilder()
                    .name(Text.literal("PopCounter Enabled"))
                    .binding(true, () -> ConfigManager.popCounterEnabled, value -> ConfigManager.popCounterEnabled = value)
                    .customController(BooleanController::new)
                    .build());

            toggles.option(Option.<Boolean>createBuilder()
                    .name(Text.literal("Ping Enabled"))
                    .binding(true, () -> ConfigManager.pingEnabled, value -> ConfigManager.pingEnabled = value)
                    .customController(BooleanController::new)
                    .build());

            toggles.option(Option.<Boolean>createBuilder()
                    .name(Text.literal("Name Visibility Enabled"))
                    .binding(true, () -> ConfigManager.nameVisibilityEnabled, value -> ConfigManager.nameVisibilityEnabled = value)
                    .customController(BooleanController::new)
                    .build());

            toggles.option(Option.<Boolean>createBuilder()
                    .name(Text.literal("NameProtect Enabled"))
                    .binding(true, () -> ConfigManager.nameProtectEnabled, value -> ConfigManager.nameProtectEnabled = value)
                    .customController(BooleanController::new)
                    .build());

            toggles.option(Option.<String>createBuilder()
                    .name(Text.literal("NameProtect Replacement"))
                    .description(OptionDescription.of(Text.literal("What your name should be replaced with")))
                    .binding("22qq", () -> ConfigManager.nameProtectReplacement, value -> ConfigManager.nameProtectReplacement = value)
                    .customController(StringController::new)
                    .build());

            return YetAnotherConfigLib.createBuilder()
                    .title(Text.literal("22qq Mod Settings"))
                    .save(ConfigManager::save)
                    .category(toggles.build())
                    .build()
                    .generateScreen(parent);
        };
    }
}
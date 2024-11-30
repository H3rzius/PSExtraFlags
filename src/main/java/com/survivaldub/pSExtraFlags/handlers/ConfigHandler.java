package com.survivaldub.pSExtraFlags.handlers;

import com.survivaldub.pSExtraFlags.PSExtraFlags;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {
    private final PSExtraFlags plugin;
    private FileConfiguration messagesFile;

    public ConfigHandler(PSExtraFlags plugin) {
        this.plugin = plugin;
        this.createDefaultFiles();
    }

    public void createDefaultFiles() {
        File file = new File(this.plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            this.plugin.saveResource("messages.yml", false);
        }
        this.messagesFile = YamlConfiguration.loadConfiguration((File)file);
    }

    public FileConfiguration getMessages() {
        return this.messagesFile;
    }
}

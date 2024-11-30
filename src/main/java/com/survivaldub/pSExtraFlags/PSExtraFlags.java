package com.survivaldub.pSExtraFlags;

import com.survivaldub.pSExtraFlags.handlers.ConfigHandler;
import com.survivaldub.pSExtraFlags.handlers.FlagHandler;
import com.survivaldub.pSExtraFlags.listeners.AnimalProtectionListener;
import com.survivaldub.pSExtraFlags.listeners.TeleportListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PSExtraFlags extends JavaPlugin {

    private static PSExtraFlags instance;
    private ConfigHandler files;

    public void onEnable() {
        instance = this;
        this.files = new ConfigHandler(this);
        FlagHandler.registerFlags();
        Bukkit.getPluginManager().registerEvents((Listener)new TeleportListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new AnimalProtectionListener(), (Plugin)this);
        this.getLogger().info("Plugin iniciado correctamente.");
    }

    public void onDisable() {
        this.getLogger().info("Plugin desactivado correctamente.");
    }

    public static PSExtraFlags getInstance() {
        return instance;
    }

    public ConfigHandler getFiles() {
        return this.files;
    }
}

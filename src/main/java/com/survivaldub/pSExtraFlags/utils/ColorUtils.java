package com.survivaldub.pSExtraFlags.utils;

import org.bukkit.ChatColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ColorUtils {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static Component translate(String message) {
        if (ColorUtils.isMiniMessage(message)) {
            message = ColorUtils.translateGradient(message);
            return miniMessage.deserialize(message);
        }
        String legacyMessage = ChatColor.translateAlternateColorCodes((char)'&', (String)message);
        return Component.text(legacyMessage);
    }

    public static String translateLegacy(String message) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)message);
    }

    public static boolean isMiniMessage(String message) {
        return message.contains("<") && message.contains(">");
    }

    private static String translateGradient(String message) {
        return message.replaceAll("<#([A-Fa-f0-9]{6})>(.*?)</#([A-Fa-f0-9]{6})>", "<gradient:#$1:#$3>$2</gradient>");
    }
}

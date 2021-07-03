package org.noxus.noxusmaintenance.utils;

import org.bukkit.ChatColor;

public class TextColor {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}

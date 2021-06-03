package org.noxus.noxusmaintenance.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.noxus.noxusmaintenance.files.FileManager;

public class ReplacerUtils {

    public static String noPermissions(){
        return ChatColor.translateAlternateColorCodes('&',
                FileManager.getLang().getString("error.no-permission"));
    }

    public static void helpMessages(CommandSender sender){

        for(String line: FileManager.getLang().getStringList("lang.help")){

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));

        }

    }

    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void reload(CommandSender sender){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                FileManager.getLang().getString("lang.reload")));
    }

}

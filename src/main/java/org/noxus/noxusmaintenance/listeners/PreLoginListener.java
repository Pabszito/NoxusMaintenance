package org.noxus.noxusmaintenance.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.noxus.noxusmaintenance.files.FileManager;

public class PreLoginListener implements Listener {

    @EventHandler
    public void preLoginPlayer(AsyncPlayerPreLoginEvent event){

        StringBuilder stringBuilder = new StringBuilder();

        if (FileManager.getConfig().getBoolean("config.enable")){

            for (String bypass : FileManager.getConfig().getStringList("config.bypass-maintenance")){

                if (bypass.equalsIgnoreCase(event.getName())){

                    event.allow();

                } else {

                    for (String line : FileManager.getConfig().getStringList("config.kick-message")){

                        stringBuilder.append(line).append("\n");

                    }

                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, stringBuilder.toString());
                }
                return;

            }

        }

    }


}

package org.noxus.noxusmaintenance.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.noxus.noxusmaintenance.files.FileManager;

public class PreLoginListener implements Listener {

    private final FileManager fileManager;

    public PreLoginListener(FileManager fileManager){
        this.fileManager = fileManager;
    }

    @EventHandler
    public void preLoginPlayer(AsyncPlayerPreLoginEvent event){

        StringBuilder stringBuilder = new StringBuilder();

        if (fileManager.getConfig().getBoolean("config.enable")){

            for (String player : fileManager.getConfig().getStringList("config.player-maintenance")){

                if (!(event.getName().equalsIgnoreCase(player))){

                    for (String line : fileManager.getConfig().getStringList("config.kick-message")){

                        stringBuilder.append(line).append("\n");

                    }

                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, stringBuilder.toString());

                    return;

                }

                event.allow();

            }

        }

    }


}

package org.noxus.noxusmaintenance.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.noxus.noxusmaintenance.files.FileManager;
import org.noxus.noxusmaintenance.handlers.MaintenanceHandler;

public class PreLoginListener implements Listener {

    private final FileManager fileManager;
    private final MaintenanceHandler handler;

    public PreLoginListener(FileManager fileManager, MaintenanceHandler handler){
        this.fileManager = fileManager;
        this.handler = handler;
    }

    @EventHandler
    public void preLoginPlayer(AsyncPlayerPreLoginEvent event){

        StringBuilder stringBuilder = new StringBuilder();

        if (fileManager.getConfig().getBoolean("config.enable")){

            if (handler.isWhitelist(event.getName())){

                event.allow();

                return;

            }

            for (String line : fileManager.getConfig().getStringList("config.kick-message")){

                stringBuilder.append(line).append("\n");

            }

            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, stringBuilder.toString());

        }

    }


}

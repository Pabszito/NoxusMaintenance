package org.noxus.noxusmaintenance.handlers;

import org.noxus.noxusmaintenance.files.FileManager;

import java.util.List;

public class MaintenanceHandler {

    private final FileManager fileManager;
    private List<String> whitelist;

    public MaintenanceHandler(FileManager fileManager){
        this.fileManager = fileManager;
    }


    public boolean addPlayer(String arg){

        whitelist = fileManager.getConfig().getStringList("config.whitelist-player");

        if (whitelist.contains(arg)){
            return false;
        }

        whitelist.add(arg);

        fileManager.getConfig().set("config.whitelist-player", whitelist);
        fileManager.getConfig().save();
        return true;
    }

    public boolean removePlayer(String arg){

        whitelist = fileManager.getConfig().getStringList("config.whitelist-player");

        if (!(whitelist.contains(arg))){
            return false;
        }

        whitelist.remove(arg);

        fileManager.getConfig().set("config.whitelist-player", whitelist);
        fileManager.getConfig().save();
        return true;

    }

    public boolean isWhitelist(String string){

        whitelist = fileManager.getConfig().getStringList("config.whitelist-player");

        return whitelist.contains(string);
    }


}

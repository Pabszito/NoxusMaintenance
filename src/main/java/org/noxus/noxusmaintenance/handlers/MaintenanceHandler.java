package org.noxus.noxusmaintenance.handlers;

import org.noxus.noxusmaintenance.files.FileManager;

import java.util.List;

public class MaintenanceHandler {

    private final FileManager fileManager;

    public MaintenanceHandler(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public boolean addPlayer(String arg){

        List<String> addList = fileManager.getConfig().getStringList("config.whitelist-player");

        if (addList.contains(arg)){
            return false;
        }

        addList.add(arg);

        fileManager.getConfig().set("config.whitelist-player", addList);
        fileManager.getConfig().save();
        return true;
    }

    public boolean removePlayer(String arg){

        List<String> removeList = fileManager.getConfig().getStringList("config.whitelist-player");

        if (!(removeList.contains(arg))){
            return false;
        }

        removeList.remove(arg);

        fileManager.getConfig().set("config.whitelist-player", removeList);
        fileManager.getConfig().save();
        return true;

    }

    public boolean isWhitelist(String string){

        List<String> list = fileManager.getConfig().getStringList("config.whitelist-player");

        return list.contains(string);
    }


}

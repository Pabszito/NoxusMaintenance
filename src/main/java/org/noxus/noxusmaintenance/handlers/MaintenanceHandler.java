package org.noxus.noxusmaintenance.handlers;

import org.noxus.noxusmaintenance.files.FileManager;

import java.util.List;

public class MaintenanceHandler {

    private final FileManager fileManager;

    public MaintenanceHandler(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public boolean add(String arg){

        if (fileManager.getConfig().getStringList("config.player-maintenance").contains(arg)){
            return false;
        }

        List<String> addList = fileManager.getConfig().getStringList("config.player-maintenance");

        addList.add(arg);

        fileManager.getConfig().set("config.player-maintenance", addList);
        fileManager.getConfig().save();
        return true;
    }

    public boolean remove(String arg){

        if (fileManager.getConfig().getStringList("config.player-maintenance").contains(arg)){
            return false;
        }

        List<String> removeList = fileManager.getConfig().getStringList("config.player-maintenance");

        removeList.remove(arg);

        fileManager.getConfig().set("config.player-maintenance", removeList);
        fileManager.getConfig().save();
        return true;

    }


}

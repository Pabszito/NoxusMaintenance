package org.noxus.noxusmaintenance;

import org.bukkit.plugin.java.JavaPlugin;
import org.noxus.noxusmaintenance.commands.MainCommand;
import org.noxus.noxusmaintenance.commands.TabCompleteCommand;
import org.noxus.noxusmaintenance.files.FileManager;
import org.noxus.noxusmaintenance.listeners.PreLoginListener;

public final class NoxusMaintenance extends JavaPlugin {

    @Override
    public void onEnable() {
        this.registerAll();

    }

    @Override
    public void onDisable() {

    }

    private void registerAll(){

        FileManager files = new FileManager(this);

        getCommand("noxusmaintenance").setExecutor(new MainCommand());
        getCommand("noxusmaintenance").setTabCompleter(new TabCompleteCommand());

        getServer().getPluginManager().registerEvents(new PreLoginListener(), this);

        files.setupFiles();

    }
}

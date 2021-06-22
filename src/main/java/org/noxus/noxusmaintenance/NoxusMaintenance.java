package org.noxus.noxusmaintenance;

import org.bukkit.plugin.java.JavaPlugin;
import org.noxus.noxusmaintenance.commands.MainCommand;
import org.noxus.noxusmaintenance.commands.TabCompleteCommand;
import org.noxus.noxusmaintenance.files.FileManager;
import org.noxus.noxusmaintenance.handlers.MaintenanceHandler;
import org.noxus.noxusmaintenance.listeners.PreLoginListener;

public final class NoxusMaintenance extends JavaPlugin {

    private final FileManager files = new FileManager(this);
    private final MaintenanceHandler handler = new MaintenanceHandler(files);

    @Override
    public void onEnable() {

        getLogger().info("");
        getLogger().info("|\\ |\\/|\\/|  | NoxusMaintenance v"+getDescription().getVersion());
        getLogger().info("| \\|/\\|  |  | By: "+getDescription().getAuthors());
        getLogger().info("");
        getLogger().info("Loading, please wait...");

        this.registerAll();
        getLogger().info("Loading succes");

    }

    @Override
    public void onDisable() {

    }

    private void registerAll(){

        getCommand("noxusmaintenance").setExecutor(new MainCommand(files, handler));
        getCommand("noxusmaintenance").setTabCompleter(new TabCompleteCommand());

        getServer().getPluginManager().registerEvents(new PreLoginListener(files), this);

        files.setupFiles();

    }
}

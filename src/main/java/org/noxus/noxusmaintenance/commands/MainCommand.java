package org.noxus.noxusmaintenance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.noxus.noxusmaintenance.files.FileManager;
import org.noxus.noxusmaintenance.handlers.MaintenanceHandler;
import org.noxus.noxusmaintenance.utils.TextColor;

import java.util.List;

public class MainCommand implements CommandExecutor {

    private final FileManager fileManager;
    private final MaintenanceHandler handler;

    public MainCommand(FileManager fileManager, MaintenanceHandler handler) {
        this.fileManager = fileManager;
        this.handler = handler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("noxusmaintenance.commands"))) {
            sender.sendMessage(TextColor.color(fileManager.getLang().getString("error.no-permission")));
            return true;
        }


        if (!(args.length > 0)) {
            for (String line : fileManager.getLang().getStringList("lang.help")) {
                sender.sendMessage(TextColor.color(line));
            }
            return true;

        }

        switch (args[0].toLowerCase()) {
            case "reload":
                fileManager.getLang().reload();
                fileManager.getConfig().reload();
                sender.sendMessage(TextColor.color(fileManager.getLang().getString("lang.reload")));
                break;
            case "on":
                if (!fileManager.getConfig().getBoolean("config.enable")) {
                    fileManager.getConfig().set("config.enable", true);
                    fileManager.getConfig().save();
                    sender.sendMessage(TextColor.color(
                            fileManager.getLang().getString("lang.maintenance-enabled")));
                    return true;
                }

                sender.sendMessage(TextColor.color(
                        fileManager.getLang().getString("error.already-enable")));
                break;

            case "off":
                if (fileManager.getConfig().getBoolean("config.enable")) {
                    fileManager.getConfig().set("config.enable", false);
                    fileManager.getConfig().save();
                    sender.sendMessage(TextColor.color(
                            fileManager.getLang().getString("lang.maintenance-disable")));
                    return true;
                }

                sender.sendMessage(TextColor.color(
                        fileManager.getLang().getString("error.already-disable")));
                break;
            case "add":
                if (!(args.length > 1)) {

                    for (String line : fileManager.getLang().getStringList("lang.help")) {
                        sender.sendMessage(TextColor.color(line));
                    }

                    return true;
                }

                if (handler.addPlayer(args[1])) {
                    sender.sendMessage(fileManager.getLang().getString("lang.player-added").
                            replace("%player%", args[1])
                    );

                    return true;
                }

                sender.sendMessage(fileManager.getLang().getString("error.already-player"));
                break;
            case "remove":
                if (!(args.length > 1)) {
                    for (String line : fileManager.getLang().getStringList("lang.help")) {
                        sender.sendMessage(TextColor.color(line));
                    }

                    return true;
                }

                if (handler.removePlayer(args[1])) {
                    sender.sendMessage(TextColor.color(
                            fileManager.getLang().getString("lang.player-remove").
                                    replace("%player%", args[1])
                    ));

                    return true;
                }

                sender.sendMessage(TextColor.color(
                        fileManager.getLang().getString("error.no-player")
                ));
                break;
            case "list":
                StringBuilder listPlayers = new StringBuilder();
                List<String> whitelist = fileManager.getConfig().getStringList("config.whitelist-player");

                for (int i = 0; i < whitelist.size(); i++) {
                    listPlayers.append(whitelist.get(i)).append(' ');
                }

                sender.sendMessage(TextColor.color("&aWhitelist players: &e" + listPlayers));
                break;
            default:
                for (String line : fileManager.getLang().getStringList("lang.help")) {
                    sender.sendMessage(TextColor.color(line));
                }

                break;
        }
        return true;
    }
}

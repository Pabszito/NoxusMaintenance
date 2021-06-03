package org.noxus.noxusmaintenance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.noxus.noxusmaintenance.files.FileManager;
import org.noxus.noxusmaintenance.utils.ReplacerUtils;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(args.length > 0)){

            ReplacerUtils.helpMessages(sender);
            return true;

        }

        switch (args[0].toLowerCase()){

            case "reload":

                FileManager.getLang().reload();
                FileManager.getConfig().reload();
                ReplacerUtils.reload(sender);
                break;

            case "on":

                if (!FileManager.getConfig().getBoolean("config.enable")){

                    FileManager.getConfig().set("config.enable", true);
                    FileManager.getConfig().save();
                    sender.sendMessage(ReplacerUtils.color(
                            FileManager.getLang().getString("lang.maintenance-enabled")));
                    return true;
                }

                sender.sendMessage(ReplacerUtils.color(
                        FileManager.getLang().getString("error.already-enable")));
                break;

            case "off":

                if (FileManager.getConfig().getBoolean("config.enable")){

                    FileManager.getConfig().set("config.enable", false);
                    FileManager.getConfig().save();
                    sender.sendMessage(ReplacerUtils.color(
                            FileManager.getLang().getString("lang.maintenance-disable")));
                    return true;
                }

                sender.sendMessage(ReplacerUtils.color(
                        FileManager.getLang().getString("error.already-disable")));
                break;

            case "add":

                if (!(args.length > 1)){

                    ReplacerUtils.helpMessages(sender);
                    return true;

                }

                if (FileManager.getConfig().getStringList("config.bypass-maintenance").contains(args[1])){

                    sender.sendMessage(FileManager.getLang().getString("error.already-player"));

                    return true;

                }

                List<String> list = FileManager.getConfig().getStringList("config.bypass-maintenance");
                list.add(args[1]);
                FileManager.getConfig().set("config.bypass-maintenance", list);

                FileManager.getConfig().save();

                sender.sendMessage(FileManager.getLang().getString("lang.player-added").
                                replace("%player%", args[1])
                );

                break;

            case "remove":

                if (!(args.length > 1)){

                    ReplacerUtils.helpMessages(sender);
                    return true;

                }

                if (!FileManager.getConfig().getStringList("config.bypass-maintenance").contains(args[1])){

                    sender.sendMessage(ReplacerUtils.color(
                            FileManager.getLang().getString("error.no-player")
                    ));

                    return true;

                }

                List<String> list2 = FileManager.getConfig().getStringList("config.bypass-maintenance");
                list2.remove(args[1]);
                FileManager.getConfig().set("config.bypass-maintenance", list2);
                FileManager.getConfig().save();

                sender.sendMessage(ReplacerUtils.color(
                        FileManager.getLang().getString("lang.player-remove").
                                replace("%player%", args[1])
                ));

                break;
            default:
                ReplacerUtils.helpMessages(sender);
                break;
        }
        return true;
    }
}

package org.noxus.noxusmaintenance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteCommand implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1) {

            list.add("reload");
            list.add("on");
            list.add("off");
            list.add("add");
            list.add("remove");
            list.add("list");
            return list;
        }
        return null;
    }
}

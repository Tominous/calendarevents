package me.nikl.calendarevents;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Niklas
 *
 * Commands for CalendarEvents
 */
public class Commands implements CommandExecutor {

    private Main plugin;

    public Commands(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        switch (args.length){
            case 1:
                if(!args[0].equalsIgnoreCase("reload")){
                    break;
                }
                if(!sender.hasPermission("calendarevents.reload")){
                    sender.sendMessage("[CalendarEvents] You have no permission!");
                    return true;
                }
                plugin.reload();
                return true;


        }
        sender.sendMessage("[CalendarEvents] There is no such command!");
        return true;
    }
}
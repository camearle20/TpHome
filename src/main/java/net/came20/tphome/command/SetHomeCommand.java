package net.came20.tphome.command;

import net.came20.tphome.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Location location = player.getLocation();
            player.sendMessage(ChatColor.AQUA + "Set your home location!");
            HomeManager.setHomeLocation(player, location);
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

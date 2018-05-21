package net.came20.tphome.command;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length > 0) {
                String name = args[0];
                Location warpLocation = WarpManager.getWarpLocation(name);
                if (warpLocation != null) {
                    player.sendMessage(ChatColor.AQUA + "Teleporting you to warp '" + name + "'");
                    BackLocationManager.setPlayerBackLocation(player);
                    player.teleport(warpLocation);
                } else {
                    player.sendMessage(ChatColor.RED + "Warp '" + name + "' not found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must specify a warp to go to!");
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

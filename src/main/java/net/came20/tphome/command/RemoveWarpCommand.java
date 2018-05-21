package net.came20.tphome.command;

import net.came20.tphome.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (args.length > 0) {
                    String name = args[0];
                    WarpManager.removeWarp(name);
                    player.sendMessage(ChatColor.AQUA + "The warp '" + name + "' was removed");
                } else {
                    player.sendMessage(ChatColor.RED + "You must specify a warp to remove!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to remove warps.");
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

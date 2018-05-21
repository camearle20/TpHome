package net.came20.tphome.command;

import net.came20.tphome.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (args.length > 0) {
                    String name = args[0];
                    WarpManager.setWarpLocation(name, player.getLocation());
                    player.sendMessage(ChatColor.AQUA + "Warp '" + name + "' set to your location");
                } else {
                    player.sendMessage(ChatColor.RED + "You must specify a name for your warp!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to set warps.");
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

package net.came20.tphome.command;

import net.came20.tphome.HomeManager;
import net.came20.tphome.BackLocationManager;
import net.came20.tphome.request.HomeRequest;
import net.came20.tphome.request.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player asker = (Player) commandSender;
            if (args.length == 0) { //Self home
                Location homeLocation = HomeManager.getHomeLocation(asker);
                if (homeLocation != null) {
                    asker.sendMessage(ChatColor.AQUA + "Teleporting you to your home.");
                    BackLocationManager.setPlayerBackLocation(asker);
                    asker.teleport(homeLocation);
                } else {
                    asker.sendMessage(ChatColor.RED + "You don't have a home set!");
                }
            } else { //Visit home
                String name = args[0];
                Player asked = Bukkit.getServer().getOnlinePlayers().stream()
                        .filter(player -> player.getDisplayName().toLowerCase().startsWith(name.toLowerCase()))
                        .findFirst()
                        .orElse(null);
                if (asked != null) { //If the player exists
                    if (HomeManager.playerHasHome(asked)) { //If the player has a home
                        HomeRequest request = new HomeRequest(asker, asked);
                        RequestManager.registerAndSend(request);
                    } else {
                        asker.sendMessage(ChatColor.RED + "Player '" + asked.getDisplayName() + "' doesn't have a home set!");
                    }
                } else {
                    asker.sendMessage(ChatColor.RED + "Could not find player '" + name + "'");
                }
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

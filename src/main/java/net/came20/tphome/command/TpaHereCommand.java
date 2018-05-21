package net.came20.tphome.command;

import net.came20.tphome.request.RequestManager;
import net.came20.tphome.request.TeleportHereRequest;
import net.came20.tphome.request.TeleportReqeust;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaHereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            if (args.length > 0) {
                String name = args[0];
                Player asker = (Player) commandSender;
                Player asked = Bukkit.getServer().getOnlinePlayers().stream()
                        .filter(player -> player.getDisplayName().toLowerCase().startsWith(name.toLowerCase()))
                        .findFirst()
                        .orElse(null);
                if (asked != null) {
                    TeleportHereRequest reqeust = new TeleportHereRequest(asker, asked);
                    RequestManager.registerAndSend(reqeust);
                } else {
                    asker.sendMessage(ChatColor.AQUA + "Could not find player '" + name + "'");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "You must specify a player to teleport to you");
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

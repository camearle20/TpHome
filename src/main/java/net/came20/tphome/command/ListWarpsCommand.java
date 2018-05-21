package net.came20.tphome.command;

import net.came20.tphome.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListWarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ArrayList<String> messages = new ArrayList<>();
            messages.add("Warps:");
            messages.addAll(WarpManager.getWarpNames());
            player.sendMessage(messages.toArray(new String[]{}));
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

package net.came20.tphome.command;

import net.came20.tphome.NoTitleUserManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleTitleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            boolean oldWantsTitle = NoTitleUserManager.getUserWantsTitle(player);
            if (oldWantsTitle) {
                NoTitleUserManager.setUserWantsTitle(player, false);
                player.sendMessage(ChatColor.AQUA + "Use titles turned " + ChatColor.RESET + ChatColor.RED + "OFF");
            } else {
                NoTitleUserManager.setUserWantsTitle(player, true);
                player.sendMessage(ChatColor.AQUA + "Use titles turned " + ChatColor.RESET + ChatColor.GREEN + "ON");
            }
            return true;
        } else {
            commandSender.sendMessage("This command must be called by a player!");
            return false;
        }
    }
}

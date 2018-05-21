package net.came20.tphome.request;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.Constants;
import net.came20.tphome.NoTitleUserManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeleportReqeust extends Request {
    public TeleportReqeust(Player asker, Player asked) {
        super(asker, asked, Constants.TP_TIMEOUT);
    }

    @Override
    public void send() {
        asker.sendMessage(ChatColor.AQUA + "Request to teleport to " + asked.getDisplayName() + " sent!");
        if (NoTitleUserManager.getUserWantsTitle(asked)) {
            asked.sendTitle(
                    ChatColor.AQUA + "Teleport Request",
                    asker.getDisplayName() + " -> You | Do '" + ChatColor.GREEN + "/accept" + ChatColor.RESET + "' or '" + ChatColor.RED + "/decline" + ChatColor.RESET + "'.",
                    10, 70, 20
            );
        } else {
            asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + " wants to teleport to you!  Do '" + ChatColor.GREEN + "/accept" + ChatColor.RESET + ChatColor.AQUA + "' or '" + ChatColor.RED + "/decline" + ChatColor.RESET + ChatColor.AQUA + "'.");
        }
    }

    @Override
    public void accept() {
        asker.sendMessage(ChatColor.GREEN + asked.getDisplayName() + " accepted your request.  Teleporting!");
        asked.sendMessage(ChatColor.AQUA + "You accepted " + asker.getDisplayName() + "'s request");
        BackLocationManager.setPlayerBackLocation(asker);
        asker.teleport(asked);
    }

    @Override
    public void decline() {
        asker.sendMessage(ChatColor.RED + asked.getDisplayName() + " declined your request.");
        asked.sendMessage(ChatColor.AQUA + "You declined " + asker.getDisplayName() + "'s request");
    }

    @Override
    public void cancel() {
        asker.sendMessage(ChatColor.AQUA + "You cancelled your request to teleport to " + asked.getDisplayName());
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + " cancelled their request to teleport to you");
    }

    @Override
    public void timeout() {
        asker.sendMessage(ChatColor.AQUA + "Your request to teleport to " + asked.getDisplayName() + " timed out!");
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + "'s request to teleport to you timed out!");
    }
}

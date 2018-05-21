package net.came20.tphome.request;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.Constants;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportHereRequest extends Request {
    public TeleportHereRequest(Player asker, Player asked) {
        super(asker, asked, Constants.TP_TIMEOUT);
    }

    @Override
    public void send() {
        asker.sendMessage(ChatColor.AQUA + "Request to teleport " + asked.getDisplayName() + " to you sent!");
        //asked.sendMessage(asker.getDisplayName() + " wants to teleport you to them!  Do '/accept' to accept, or '/decline' to decline.");
        asked.sendTitle(
                ChatColor.AQUA + "Teleport Request",
                "You -> " + asker.getDisplayName() + " | Do '" + ChatColor.GREEN + "/accept" + ChatColor.RESET + "' or '" + ChatColor.RED + "/decline" + ChatColor.RESET + "'.",
                10, 70, 20
        );
    }

    @Override
    public void accept() {
        asker.sendMessage(ChatColor.GREEN + asked.getDisplayName() + " accepted your request.");
        asked.sendMessage(ChatColor.AQUA + "You accepted " + asker.getDisplayName() + "'s request.  Teleporting!");
        BackLocationManager.setPlayerBackLocation(asked);
        asked.teleport(asker);
    }

    @Override
    public void decline() {
        asker.sendMessage(ChatColor.RED + asked.getDisplayName() + " declined your request.");
        asked.sendMessage(ChatColor.AQUA + "You declined " + asker.getDisplayName() + "'s request");
    }

    @Override
    public void cancel() {
        asker.sendMessage(ChatColor.AQUA + "You cancelled your request to teleport " + asked.getDisplayName() + " to you");
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + " cancelled their request to teleport you to them");
    }

    @Override
    public void timeout() {
        asker.sendMessage(ChatColor.AQUA + "Your request to teleport " + asked.getDisplayName() + " to you timed out!");
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + "'s request to teleport you to them timed out!");
    }
}

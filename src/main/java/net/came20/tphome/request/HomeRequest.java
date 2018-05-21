package net.came20.tphome.request;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.Constants;
import net.came20.tphome.HomeManager;
import net.came20.tphome.NoTitleUserManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HomeRequest extends Request {
    public HomeRequest(Player asker, Player asked) {
        super(asker, asked, Constants.TP_TIMEOUT);
    }

    @Override
    public void send() {
        asker.sendMessage(ChatColor.AQUA + "Request to visit " + asked.getDisplayName() + "'s home sent!");
        if (NoTitleUserManager.getUserWantsTitle(asked)) {
            asked.sendTitle(
                    ChatColor.AQUA + "Teleport Request",
                    asker.getDisplayName() + " -> Your home | Do '" + ChatColor.GREEN + "/accept" + ChatColor.RESET + "' or '" + ChatColor.RED + "/decline" + ChatColor.RESET + "'.",
                    10, 70, 20
            );
        } else {
            asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + " wants to visit your home!  Do '" + ChatColor.GREEN + "/accept" + ChatColor.RESET + ChatColor.AQUA + "' or '" + ChatColor.RED + "/decline" + ChatColor.RESET + ChatColor.AQUA + "'.");
        }
    }

    @Override
    public void accept() {
        asker.sendMessage(ChatColor.GREEN + asked.getDisplayName() + " accepted your request.  Teleporting!");
        asked.sendMessage(ChatColor.AQUA + "You accepted " + asker.getDisplayName() + "'s request");
        Location location = HomeManager.getHomeLocation(asked);
        BackLocationManager.setPlayerBackLocation(asker);
        asker.teleport(location);
    }

    @Override
    public void decline() {
        asker.sendMessage(ChatColor.RED + asked.getDisplayName() + " declined your request.");
        asked.sendMessage(ChatColor.AQUA + "You declined " + asker.getDisplayName() + "'s request");
    }

    @Override
    public void cancel() {
        asker.sendMessage(ChatColor.AQUA + "You cancelled your request to visit " + asked.getDisplayName() + "'s home");
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + " cancelled their request to visit your home");
    }

    @Override
    public void timeout() {
        asker.sendMessage(ChatColor.AQUA + "Your request to visit " + asked.getDisplayName() + "'s home timed out!");
        asked.sendMessage(ChatColor.AQUA + asker.getDisplayName() + "'s request to visit your home timed out!");
    }
}

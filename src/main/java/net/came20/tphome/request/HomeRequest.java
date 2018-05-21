package net.came20.tphome.request;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.Constants;
import net.came20.tphome.HomeManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HomeRequest extends Request {
    public HomeRequest(Player asker, Player asked) {
        super(asker, asked, Constants.TP_TIMEOUT);
    }

    @Override
    public void send() {
        asker.sendMessage("Request to visit " + asked.getDisplayName() + "'s home sent!");
        //asked.sendMessage(asker.getDisplayName() + " wants to visit your home!  Do '/accept' to accept, or '/decline' to decline.");
        asked.sendTitle(
                "Teleport Request",
                asker.getDisplayName() + " -> Your home | Do '/accept' or '/decline'.",
                10, 70, 20
        );
    }

    @Override
    public void accept() {
        asker.sendMessage(asked.getDisplayName() + " accepted your request.  Teleporting!");
        asked.sendMessage("You accepted " + asker.getDisplayName() + "'s request");
        Location location = HomeManager.getHomeLocation(asked);
        BackLocationManager.setPlayerBackLocation(asker);
        asker.teleport(location);
    }

    @Override
    public void decline() {
        asker.sendMessage(asked.getDisplayName() + " declined your request.");
        asked.sendMessage("You declined " + asker.getDisplayName() + "'s request");
    }

    @Override
    public void cancel() {
        asker.sendMessage("You cancelled your request to visit " + asked.getDisplayName() + "'s home");
        asked.sendMessage(asker.getDisplayName() + " cancelled their request to visit your home");
    }

    @Override
    public void timeout() {
        asker.sendMessage("Your request to visit " + asked.getDisplayName() + "'s home timed out!");
        asked.sendMessage(asker.getDisplayName() + "'s request to visit your home timed out!");
    }
}

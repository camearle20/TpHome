package net.came20.tphome.request;

import net.came20.tphome.BackLocationManager;
import net.came20.tphome.Constants;
import org.bukkit.entity.Player;

public class TeleportReqeust extends Request {
    public TeleportReqeust(Player asker, Player asked) {
        super(asker, asked, Constants.TP_TIMEOUT);
    }

    @Override
    public void send() {
        asker.sendMessage("Request to teleport to " + asked.getDisplayName() + " sent!");
        //asked.sendMessage(asker.getDisplayName() + " wants to teleport to you!  Do '/accept' to accept, or '/decline' to decline.");
        asked.sendTitle(
                "Teleport Request",
                asker.getDisplayName() + " -> You | Do '/accept' or '/decline'.",
                10, 70, 20
        );
    }

    @Override
    public void accept() {
        asker.sendMessage(asked.getDisplayName() + " accepted your request.  Teleporting!");
        asked.sendMessage("You accepted " + asker.getDisplayName() + "'s request");
        BackLocationManager.setPlayerBackLocation(asker);
        asker.teleport(asked);
    }

    @Override
    public void decline() {
        asker.sendMessage(asked.getDisplayName() + " declined your request.");
        asked.sendMessage("You declined " + asker.getDisplayName() + "'s request");
    }

    @Override
    public void cancel() {
        asker.sendMessage("You cancelled your request to teleport to " + asked.getDisplayName());
        asked.sendMessage(asker.getDisplayName() + " cancelled their request to teleport to you");
    }

    @Override
    public void timeout() {
        asker.sendMessage("Your request to teleport to " + asked.getDisplayName() + " timed out!");
        asked.sendMessage(asker.getDisplayName() + "'s request to teleport to you timed out!");
    }
}

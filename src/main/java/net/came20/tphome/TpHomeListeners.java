package net.came20.tphome;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class TpHomeListeners implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        BackLocationManager.setPlayerBackLocation(e.getEntity());
    }
}

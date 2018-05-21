package net.came20.tphome;

import net.came20.tphome.request.RequestManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TpHomeListeners implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        BackLocationManager.setPlayerBackLocation(e.getEntity());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        RequestManager.handleCancel(e.getPlayer());
    }
}

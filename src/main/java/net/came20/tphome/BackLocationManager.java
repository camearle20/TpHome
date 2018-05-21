package net.came20.tphome;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BackLocationManager {
    private static HashMap<Player, Location> backLocations = new HashMap<>();

    public static void setPlayerBackLocation(Player player) {
        backLocations.put(player, player.getLocation());
    }

    public static Location getPlayerBackLocation(Player player) {
        return backLocations.getOrDefault(player, null);
    }

    public static void teleportPlayerBack(Player player) {
        Location backLocation = backLocations.getOrDefault(player, null); //Get their current back location
        if (backLocation != null) {
            player.sendMessage("Teleporting you back");
            setPlayerBackLocation(player); //Set their back location to their current location
            player.teleport(backLocation); //Teleport them to their back location
        } else {
            player.sendMessage("You don't have a back location!");
        }
    }
}

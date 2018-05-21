package net.came20.tphome;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;

public class HomeManager {
    private static Properties store = new Properties();

    synchronized public static void loadHomes(File f) {
        try {
            f.createNewFile();
            FileInputStream fis = new FileInputStream(f);
            store.load(fis);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error loading homes from file '" + f.getAbsolutePath() + "'", e);
        }
    }

    synchronized public static void saveHomes(File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            store.store(fos, "Player Home Locations (world,x,y,z,yaw,pitch)");
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error saving homes to file '" + f.getAbsolutePath() + "'", e);
        }
    }

    synchronized public static boolean playerHasHome(Player player) {
        return store.containsKey(player.getUniqueId().toString());
    }

    synchronized public static Location getHomeLocation(Player player) {
        if (playerHasHome(player)) {
            String loc = store.getProperty(player.getUniqueId().toString());
            return LocationAdapter.deserialize(loc);
        } else {
            return null;
        }
    }

    synchronized public static void setHomeLocation(Player player, Location location) {
        String loc = LocationAdapter.serialize(location);
        store.setProperty(player.getUniqueId().toString(), loc);
    }

    synchronized public static void removeHome(Player player) {
        store.remove(player.getUniqueId().toString());
    }
}

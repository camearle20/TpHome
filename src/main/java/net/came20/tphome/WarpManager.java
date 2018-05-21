package net.came20.tphome;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

public class WarpManager {
    private static Properties store = new Properties();

    synchronized public static void loadWarps(File f) {
        try {
            f.createNewFile();
            FileInputStream fis = new FileInputStream(f);
            store.load(fis);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error loading warps from file '" + f.getAbsolutePath() + "'", e);
        }
    }

    synchronized public static void saveWarps(File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            store.store(fos, "Warp Locations (world,x,y,z,yaw,pitch)");
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error saving warps to file '" + f.getAbsolutePath() + "'", e);
        }
    }

    synchronized public static boolean warpExists(String name) {
        return store.containsKey(name);
    }

    synchronized public static Location getWarpLocation(String name) {
        if (warpExists(name)) {
            String loc = store.getProperty(name);
            return LocationAdapter.deserialize(loc);
        } else {
            return null;
        }
    }

    synchronized public static void setWarpLocation(String name, Location location) {
        String loc = LocationAdapter.serialize(location);
        store.setProperty(name, loc);
    }

    synchronized public static void removeWarp(String name) {
        store.remove(name);
    }

    synchronized public static Set<String> getWarpNames() {
        return store.stringPropertyNames();
    }
}

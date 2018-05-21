package net.came20.tphome;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;

public class NoTitleUserManager {
        private static Properties store = new Properties();

        synchronized public static void loadUsers(File f) {
            try {
                f.createNewFile();
                FileInputStream fis = new FileInputStream(f);
                store.load(fis);
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error loading no title users from file '" + f.getAbsolutePath() + "'", e);
            }
        }

        synchronized public static void saveUsers(File f) {
            try {
                FileOutputStream fos = new FileOutputStream(f);
                store.store(fos, "No title users");
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error saving no title users to file '" + f.getAbsolutePath() + "'", e);
            }
        }

        synchronized public static boolean getUserWantsTitle(Player player) {
            return !store.containsKey(player.getUniqueId().toString()) || store.getProperty(player.getUniqueId().toString()).equalsIgnoreCase("true");
        }

        synchronized public static void setUserWantsTitle(Player player, boolean wantsTitle) {
            store.setProperty(player.getUniqueId().toString(), Boolean.toString(wantsTitle));
        }
}

package net.came20.tphome;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationAdapter {
    private static String csv(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i < strings.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String serialize(Location l) {
        return csv(
                l.getWorld().getName(),
                Double.toString(l.getX()),
                Double.toString(l.getY()),
                Double.toString(l.getZ()),
                Float.toString(l.getYaw()),
                Float.toString(l.getPitch())
        );

    }

    public static Location deserialize(String s) {
        String[] split = s.split(",");
        World world = Bukkit.getServer().getWorld(split[0]);
        double x = Double.parseDouble(split[1]);
        double y = Double.parseDouble(split[2]);
        double z = Double.parseDouble(split[3]);
        float yaw = Float.parseFloat(split[4]);
        float pitch = Float.parseFloat(split[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }
}

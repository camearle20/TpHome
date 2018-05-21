package net.came20.tphome;

import net.came20.tphome.command.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class TpHome extends JavaPlugin {
    private static Plugin plugin;
    private static Logger logger;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        logger = Bukkit.getLogger();

        //Register listener
        Bukkit.getServer().getPluginManager().registerEvents(new TpHomeListeners(), this);

        //Register commands
        getCommand(Commands.HOME).setExecutor(new HomeCommand());
        getCommand(Commands.SET_HOME).setExecutor(new SetHomeCommand());
        getCommand(Commands.BACK).setExecutor(new BackCommand());
        getCommand(Commands.ACCEPT).setExecutor(new AcceptCommand());
        getCommand(Commands.DECLINE).setExecutor(new DeclineCommand());
        getCommand(Commands.CANCEL).setExecutor(new CancelCommand());
        getCommand(Commands.TPA).setExecutor(new TpaCommand());
        getCommand(Commands.TPA_HERE).setExecutor(new TpaHereCommand());
        getCommand(Commands.SET_WARP).setExecutor(new SetWarpCommand());
        getCommand(Commands.WARP).setExecutor(new WarpCommand());
        getCommand(Commands.REMOVE_HOME).setExecutor(new RemoveHomeCommand());
        getCommand(Commands.REMOVE_WARP).setExecutor(new RemoveWarpCommand());
        getCommand(Commands.LIST_WARPS).setExecutor(new ListWarpsCommand());
        getCommand(Commands.TOGGLE_TITLES).setExecutor(new ToggleTitleCommand());

        //Load files
        HomeManager.loadHomes(new File("plugins/homes.props"));
        WarpManager.loadWarps(new File("plugins/warps.props"));
        NoTitleUserManager.loadUsers(new File("plugins/noTitles.props"));
    }

    @Override
    public void onDisable() {
        //Save files
        HomeManager.saveHomes(new File("plugins/homes.props"));
        WarpManager.saveWarps(new File("plugins/warps.props"));
        NoTitleUserManager.saveUsers(new File("plugins/noTitles.props"));
        plugin = null;
    }
}

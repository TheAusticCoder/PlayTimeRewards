// 
// Decompiled by Procyon v0.5.36
// 

package me.isnow.playtime;

import org.bukkit.plugin.PluginManager;
import org.bukkit.event.Listener;
import me.isnow.playtime.events.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.command.CommandExecutor;
import me.isnow.playtime.commands.Playtime;
import java.util.Objects;
import org.bukkit.command.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import net.milkbowl.vault.economy.Economy;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static final Logger log;
    private static Economy econ;
    public static Main instance;
    
    public void onEnable() {
        (Main.instance = this).registerCommands();
        this.RegisterEvents();
        if (!this.setupEconomy()) {
            Main.log.severe(String.format("[%s] - Disabled due to no Vault dependency or economy found!", this.getDescription().getName()));
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        this.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("Time and rewards loaded!!");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("");
    }
    
    public void registerCommands() {
        Objects.requireNonNull(this.getCommand("playtime")).setExecutor((CommandExecutor)new Playtime());
    }
    
    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (rsp == null) {
            return false;
        }
        Main.econ = (Economy)rsp.getProvider();
        return Main.econ != null;
    }
    
    public Economy getEconomy() {
        return Main.econ;
    }
    
    public void RegisterEvents() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new PlayerJoinEvent(), (Plugin)this);
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    static {
        log = Logger.getLogger("Minecraft");
        Main.econ = null;
    }
}

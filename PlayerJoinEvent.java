// 
// Decompiled by Procyon v0.5.36
// 

package me.isnow.playtime.events;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.isnow.playtime.Main;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener
{
    @EventHandler
    public void onJoin(final org.bukkit.event.player.PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        if (Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()) == null) {
            Main.getInstance().getConfig().createSection(p.getUniqueId().toString());
            Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("seconds", (Object)0);
            Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("minutes", (Object)0);
            Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("hours", (Object)0);
            Main.getInstance().saveConfig();
        }
        new BukkitRunnable() {
            int sec = (int)Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).get("seconds");
            int minutes = (int)Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).get("minutes");
            int hours = (int)Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).get("hours");
            
            public void run() {
                ++this.sec;
                Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("seconds", (Object)this.sec);
                if (this.sec == 60) {
                    this.sec = 0;
                    ++this.minutes;
                    Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("minutes", (Object)this.minutes);
                }
                if (this.minutes == 60) {
                    this.minutes = 0;
                    ++this.hours;
                    Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("hours", (Object)this.hours);
                    Main.getInstance().getConfig().getConfigurationSection(p.getUniqueId().toString()).set("minutes", (Object)this.minutes);
                    Main.getInstance().getEconomy().depositPlayer((OfflinePlayer)p, 10.0);
                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "PlayTime" + ChatColor.GRAY + "] " + ChatColor.GOLD + "You Just got 10$ from playing on this server!");
                }
                Main.getInstance().saveConfig();
                if (!p.isOnline()) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Main.getInstance(), 0L, 20L);
    }
}

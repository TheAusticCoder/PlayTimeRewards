// 
// Decompiled by Procyon v0.5.36
// 

package me.isnow.playtime.commands;

import org.bukkit.ChatColor;
import me.isnow.playtime.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Playtime implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[PlayTime] Console can't execute this command!");
            return true;
        }
        final int seconds = (int)Main.getInstance().getConfig().getConfigurationSection(((Player)sender).getUniqueId().toString()).get("seconds");
        final int minutes = (int)Main.getInstance().getConfig().getConfigurationSection(((Player)sender).getUniqueId().toString()).get("minutes");
        final int hours = (int)Main.getInstance().getConfig().getConfigurationSection(((Player)sender).getUniqueId().toString()).get("hours");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cPlayTime&7] &6Seconds: &8" + Integer.toString(seconds) + " &6Minutes: &8" + Integer.toString(minutes) + " &6Hours: &8" + Integer.toString(hours)));
        return true;
    }
}

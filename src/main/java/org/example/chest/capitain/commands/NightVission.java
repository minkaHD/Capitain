package org.example.chest.capitain.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVission implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getServer().getPlayer(args[0]);

            if (player != null && player.isOnline()) {
                 refreshEffectForPlayer(player, sender);
            }
            else
                sender.sendMessage(ChatColor.RED + "Der Spieler ist nicht online");

        }else {
            Player player = (Player) sender;
            refreshEffectForPlayer(player, sender);
        }

        return false;
    }
    public void refreshEffectForPlayer(Player player, CommandSender sender) {
        PotionEffectType effectType = PotionEffectType.NIGHT_VISION;
        if (!player.hasPotionEffect(effectType)) {
            PotionEffect effect = new PotionEffect(effectType, Integer.MAX_VALUE, 2, true);
            player.addPotionEffect(effect);

            sender.sendMessage(ChatColor.DARK_GREEN + "Nachtsicht " + ChatColor.GREEN + "wurde hinzugefügt");
        } else {
            player.removePotionEffect(effectType);
            sender.sendMessage(ChatColor.DARK_RED + "Nachtsicht " + ChatColor.RED + "wurde entfernt");
        }
    }
}

package org.example.chest.capitain.commands;
import org.bukkit.inventory.Inventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Backpack implements CommandExecutor {

    private final Inventory globalBackpack;

    public Backpack(Inventory globalBackpack) {
        this.globalBackpack = globalBackpack;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(globalBackpack);
            player.sendMessage("Du hast das globale Backpack geöffnet!");
            return true;
        } else {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return true;
        }
    }
}

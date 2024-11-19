package org.example.chest.capitain;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.chest.capitain.commands.Glow;
import org.example.chest.capitain.commands.NightVission;

import java.util.*;

// AIzaSyCqZyII5jkJH-08UfSz08lzZf9P0NIDH0g

public final class Capitain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("glow").setExecutor(new Glow());
        getCommand("cheat").setExecutor(new NightVission());
        getCommand("night").setExecutor(new NightVission());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Nachricht des Spielers abrufen
        String message = event.getMessage();

        // Prüfen, ob die Nachricht "@ai" enthält
        if (message.contains("@ai")) {
            // Spieler, der die Nachricht gesendet hat
            event.getPlayer().sendMessage("AI erkannt! Was möchtest du wissen?");

            // Optional: Nachricht im Chat unterdrücken
            event.setCancelled(true);
        }
    }
}

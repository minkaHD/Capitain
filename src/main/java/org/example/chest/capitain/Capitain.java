package org.example.chest.capitain;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.chest.capitain.commands.Glow;
import org.example.chest.capitain.commands.NightVission;
import org.example.chest.capitain.commands.Backpack;

import java.io.File;
import java.io.IOException;

// AIzaSyCqZyII5jkJH-08UfSz08lzZf9P0NIDH0g

public final class Capitain extends JavaPlugin implements Listener {

    private Inventory backpack;
    private File saveFile;

    @Override
    public void onEnable() {
        // getServer().getPluginManager().registerEvents(this, this);

        saveFile = new File(getDataFolder(), "global_backpack.yml");
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        backpack = loadBackpack();

        getCommand("glow").setExecutor(new Glow());
        getCommand("cheat").setExecutor(new NightVission());
        getCommand("night").setExecutor(new NightVission());
        getCommand("backpack").setExecutor(new Backpack(backpack));
    }

    @Override
    public void onDisable() {
        saveBackpack(backpack);

    }

    private Inventory loadBackpack() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Global Backpack");
        FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (config.contains("backpack.slot" + i)) {
                inventory.setItem(i, config.getItemStack("backpack.slot" + i));
            }
        }

        return inventory;
    }

    private void saveBackpack(Inventory inventory) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                config.set("backpack.slot" + i, item);
            } else {
                config.set("backpack.slot" + i, null);
            }
        }

        try {
            config.save(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        if (message.contains("@ai")) {
            event.getPlayer().sendMessage("AI erkannt! Was möchtest du wissen?");
            event.setCancelled(true);
        }
    }
    */
}

package de.project_minecraft.commandDiscord.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * The type Edit command.
 */
public class EditCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    /**
     * Instantiates a new Edit command.
     *
     * @param plugin the plugin
     */
    public EditCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!sender.hasPermission("discord.admin")){
            sender.sendMessage("§cYou dont have permissions to execute that command.");
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage("§cUsage:: /discord config <key> <value>");
            return false;
        }
        String key = args[1];  // Der Konfigurations-Schlüssel
        String value = args[2];  // Der Wert, der gesetzt werden soll

        // Die Konfiguration holen
        FileConfiguration config = plugin.getConfig();
        if (config.contains(key)) {
            if (key.contains("link")) {
                if (checkLink(value)) {
                    // Gültiger Discord-Link – setze und speichere
                    config.set(key, value);
                    plugin.saveConfig();
                    sender.sendMessage("§aThe value for " + key + " has been set to " + value + " and saved!");
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "discord reload");
                    sender.sendMessage("§aThe config was reloaded.");
                } else {
                    // Ungültiger Link – Fehlermeldung
                    TextComponent message = Component.text("Your value for the link key is not a valid Discord invite link!")
                            .color(TextColor.color(0xFF000E))
                            .decorate(TextDecoration.BOLD);
                    sender.sendMessage(message);
                }
            } else {
                // Kein Link – direkt speichern
                config.set(key, value);
                plugin.saveConfig();
                sender.sendMessage("§aThe value for " + key + " has been set to " + value + " and saved!");
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "discord reload");
                sender.sendMessage("§aThe config was reloaded.");
            }
        } else {
            // Ungültiger Key
            sender.sendMessage("§cInvalid key: '" + key + "'. Please choose an existing key.");
        }
        return true;
    }
    private boolean checkLink(String link){
        return link.matches("^https://discord\\.gg/[a-zA-Z0-9]+$");
    }
}

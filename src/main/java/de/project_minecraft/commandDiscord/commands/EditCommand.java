package de.project_minecraft.commandDiscord.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class EditCommand implements CommandExecutor {
    private final JavaPlugin plugin;
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
            // Setze den Wert in der Konfiguration
            config.set(key, value);

            // Speichern der Konfiguration
            plugin.saveConfig();

            // Erfolgsnachricht an den Sender senden
            sender.sendMessage("§aThe value for " + key + " has been set to " + value + " and saved!");

            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "discord reload");
            sender.sendMessage("§aThe config was reloaded.");
        } else {
            // Key existiert nicht -> Fehlermeldung
            sender.sendMessage("§cInvalid key: '" + key + "'. Please choose an existing key.");
        }




        return true;
    }
}

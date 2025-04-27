package de.project_minecraft.commandDiscord.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class editCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public editCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!sender.hasPermission("discord.admin")){
            sender.sendMessage("§cDu hast keine Berechtigung, diese Konfiguration zu bearbeiten.");
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage("§cVerwendung: /discord config <key> <value>");
            return false;
        }
        String key = args[0];  // Der Konfigurations-Schlüssel
        String value = args[1];  // Der Wert, der gesetzt werden soll

        // Die Konfiguration holen
        FileConfiguration config = plugin.getConfig();
        if (config.contains(key)) {
            // Setze den Wert in der Konfiguration
            config.set(key, value);

            // Speichern der Konfiguration
            plugin.saveConfig();

            // Erfolgsnachricht an den Sender senden
            sender.sendMessage("§aDer Wert für " + key + " wurde auf " + value + " gesetzt und gespeichert!");

            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "discord reload");
            sender.sendMessage("§aDie Konfiguration wurde erfolgreich neu geladen.");
        } else {
            // Key existiert nicht -> Fehlermeldung
            sender.sendMessage("§cUngültiger Key: '" + key + "'. Bitte wähle einen existierenden Schlüssel.");
        }




        return true;
    }
}

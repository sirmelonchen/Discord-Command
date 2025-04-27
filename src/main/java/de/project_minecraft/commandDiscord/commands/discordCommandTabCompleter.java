package de.project_minecraft.commandDiscord.commands;

import de.project_minecraft.commandDiscord.CommandDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class discordCommandTabCompleter implements TabCompleter {
    private final CommandDiscord plugin;

    public discordCommandTabCompleter(CommandDiscord plugin) {
        this.plugin = plugin;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        // Wenn der Befehl nur "/discord" ist, zeigen wir Unterbefehle an
        if (args.length == 1) {
            // Unterbefehle: "reload", "ping"
            if (sender.hasPermission("discord.admin")){
                completions.add("edit");
                completions.add("reload");
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
            FileConfiguration config = plugin.getConfig();
            // Hole alle Schlüsselnamen aus der Konfiguration
            for (String key : config.getKeys(false)) {
                if (key.startsWith(args[1])) {  // Filttere die Konfiguration anhand der Eingabe des Spielers
                    completions.add(key);
                }
            }
        }

        return completions;
    }
}
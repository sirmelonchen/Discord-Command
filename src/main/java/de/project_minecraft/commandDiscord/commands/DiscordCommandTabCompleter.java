package de.project_minecraft.commandDiscord.commands;

import de.project_minecraft.commandDiscord.CommandDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Discord command tab completer.
 */
public class DiscordCommandTabCompleter implements TabCompleter {
    private final CommandDiscord plugin;

    /**
     * Instantiates a new Discord command tab completer.
     *
     * @param plugin the plugin
     */
    public DiscordCommandTabCompleter(CommandDiscord plugin) {
        this.plugin = plugin;
    }
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> completions = new ArrayList<>();

        // Wenn der Befehl nur "/discord" ist, zeigen wir Unterbefehle an
        if (args.length == 1) {
            if (sender.hasPermission("discord.admin")) {
                if ("edit".startsWith(args[0].toLowerCase())) {
                    completions.add("edit");
                }
                if ("reload".startsWith(args[0].toLowerCase())) {
                    completions.add("reload");
                }
            }
            if ("help".startsWith(args[0].toLowerCase())) {
                completions.add("help");
            }
        }
        else if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
            FileConfiguration config = plugin.getConfig();
            for (String key : config.getKeys(false)) {
                if (key.toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(key);
                }
            }
        }


        return completions;
    }
}
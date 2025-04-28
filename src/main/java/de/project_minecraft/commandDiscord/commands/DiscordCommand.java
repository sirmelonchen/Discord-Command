package de.project_minecraft.commandDiscord.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public DiscordCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            String link = plugin.getConfig().getString("discord_link");
            assert link != null;
            sender.sendMessage(Component.text("Discord Link: ", NamedTextColor.AQUA)
                    .append(Component.text(link, NamedTextColor.YELLOW)
                            .decorate(TextDecoration.BOLD)));
            return true;
        }

        // Unterbefehl "reload"
        if (args[0].equalsIgnoreCase("reload")) {
            return new ReloadCommand(plugin).onCommand(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("edit")) {
            return new EditCommand(plugin).onCommand(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("help")) {
            return new HelpCommand(plugin).onCommand(sender, command, label, args);
        }

        sender.sendMessage("§cUnbekannter Unterbefehl! Verwende: /discord help");
        return false;
    }
}

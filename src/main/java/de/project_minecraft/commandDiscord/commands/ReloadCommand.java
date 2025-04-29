package de.project_minecraft.commandDiscord.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public ReloadCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission("discord.admin")){
            sender.sendMessage("§cYou dont have permissions to execute that command.");
            return false;
        }
        plugin.reloadConfig();
        sender.sendMessage(Component.text("Reload Complete!", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        return true;
    }
}
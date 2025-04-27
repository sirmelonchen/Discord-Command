package de.project_minecraft.commandDiscord.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class reloadCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public reloadCommand(JavaPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("discord.admin")){
            sender.sendMessage("§cDu hast keine Berechtigung, diese Konfiguration zu bearbeiten.");
            return false;
        }
        plugin.reloadConfig();
        sender.sendMessage(Component.text("Reload Complete!", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        return true;
    }
}
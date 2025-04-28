
package de.project_minecraft.commandDiscord;

import de.project_minecraft.commandDiscord.commands.DiscordCommand;
import de.project_minecraft.commandDiscord.commands.DiscordCommandTabCompleter;
import de.project_minecraft.commandDiscord.misc.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;

public final class CommandDiscord extends JavaPlugin {

    private static CommandDiscord instance;

    @Override
    public void onEnable() {
        instance = this;
        int pluginId = 25655;
        Metrics metrics = new Metrics(this, pluginId);
        // Commands registrieren
        getCommand("discord").setExecutor(new DiscordCommand(this));
        getCommand("discord").setTabCompleter(new DiscordCommandTabCompleter(this));  // TabCompleter für /discord und Unterbefehle
        saveDefaultConfig();
        getLogger().info("Plugin aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Deaktiviert!");
        // Plugin shutdown logic
    }
    public static CommandDiscord getInstance(){
        return instance;
    }

}
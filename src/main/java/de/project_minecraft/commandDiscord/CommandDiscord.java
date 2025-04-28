
package de.project_minecraft.commandDiscord;

import de.project_minecraft.commandDiscord.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandDiscord extends JavaPlugin {

    private static CommandDiscord instance;

    @Override
    public void onEnable() {
        instance = this;
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
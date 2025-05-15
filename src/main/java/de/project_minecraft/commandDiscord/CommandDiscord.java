
package de.project_minecraft.commandDiscord;

import de.project_minecraft.commandDiscord.commands.DiscordCommand;
import de.project_minecraft.commandDiscord.commands.DiscordCommandTabCompleter;
import de.project_minecraft.commandDiscord.listeners.AdminJoinListener;
import de.project_minecraft.commandDiscord.misc.Metrics;
import de.project_minecraft.commandDiscord.misc.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The type Command discord.
 */
public final class CommandDiscord extends JavaPlugin {

    private static CommandDiscord instance;

    @Override
    public void onEnable() {
        instance = this;
        boolean bStatsEnabled = getConfig().getBoolean("bstats-enabled", true);

        // Commands registrieren
        getCommand("discord").setExecutor(new DiscordCommand(this));
        getCommand("discord").setTabCompleter(new DiscordCommandTabCompleter(this));  // TabCompleter für /discord und Unterbefehle
        saveDefaultConfig();
        VersionChecker versionChecker = new VersionChecker(this);
        versionChecker.checkForUpdate();
        if (bStatsEnabled) {
            // bStats initialisieren, falls die Einstellung aktiviert ist
            int pluginId = 25655; // Ersetze dies mit deiner eigenen Plugin-ID von bStats
            Metrics metrics = new Metrics(this, pluginId);
        } else {
            // bStats deaktivieren (bei Bedarf, aber in den meisten Fällen wird das automatisch durch bStats gehandhabt)
            getLogger().warning("bStats was disabled by user.");
        }
        Bukkit.getScheduler().runTaskLater(this, () -> {
            getServer().getPluginManager().registerEvents(new AdminJoinListener(versionChecker), this);
        }, 60L);
        getLogger().info("Discord Command Plugin activated!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Discord Command Plugin activated!");
        // Plugin shutdown logic
    }

    /**
     * Get instance command discord.
     *
     * @return the command discord
     */
    public static CommandDiscord getInstance(){
        return instance;
    }

}
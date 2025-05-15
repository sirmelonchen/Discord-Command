package de.project_minecraft.commandDiscord.listeners;

import de.project_minecraft.commandDiscord.misc.VersionChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

/**
 * The type Admin join listener.
 */
public class AdminJoinListener implements Listener {

    private final VersionChecker versionChecker;

    /**
     * Instantiates a new Admin join listener.
     *
     * @param versionChecker the version checker
     */
    public AdminJoinListener(VersionChecker versionChecker) {
        this.versionChecker = versionChecker;
    }

    /**
     * On admin join.
     *
     * @param event the event
     */
    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("discord.admin") && versionChecker.isUpdateAvailable()) {
            player.sendMessage("§c[Discord-Command] A new version is available: §e" + versionChecker.getLatestVersionString());
        }
    }
}

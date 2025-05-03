package de.project_minecraft.commandDiscord.listeners;

import de.project_minecraft.commandDiscord.misc.VersionChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class AdminJoinListener implements Listener {

    private final VersionChecker versionChecker;

    public AdminJoinListener(VersionChecker versionChecker) {
        this.versionChecker = versionChecker;
    }

    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("discord.admin") && versionChecker.isUpdateAvailable()) {
            player.sendMessage("§c[Discord-Command] A new version is available: §e" + versionChecker.getLatestVersionString());
        }
    }
}

package de.project_minecraft.commandDiscord.misc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionChecker {

    private static final String VERSION_URL = "https://jenkins.beugsiud.de/job/Discord%20Command/lastStableBuild/execution/node/3/ws/version.txt";
    private final JavaPlugin plugin;

    public VersionChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void checkForUpdate() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL(VERSION_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String latestVersion = in.readLine().trim();
                    String currentVersion = plugin.getDescription().getVersion();

                    if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                        plugin.getLogger().warning("A new version is available: " + latestVersion + " (installed: " + currentVersion + ")");
                    } else {
                        plugin.getLogger().info("You are using the newest version.");
                    }
                }
            } catch (IOException e) {
                plugin.getLogger().warning("Can't get update informations: " + e.getMessage());
            }
        });
    }
}

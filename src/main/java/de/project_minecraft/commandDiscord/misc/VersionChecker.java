package de.project_minecraft.commandDiscord.misc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Version checker.
 */
public class VersionChecker {
    private static final String VERSION_URL = "https://raw.githubusercontent.com/sirmelonchen/Discord-Command/main/version.txt";
    private final JavaPlugin plugin;

    private boolean updateAvailable = false;
    private String latestVersionString = "";

    /**
     * Instantiates a new Version checker.
     *
     * @param plugin the plugin
     */
    public VersionChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Check for update.
     */
    public void checkForUpdate() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL(VERSION_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String latestVersion = in.readLine().trim();
                    String currentVersion = plugin.getDescription().getVersion();

                    int currentRev = extractRevision(currentVersion);
                    int latestRev = extractRevision(latestVersion);

                    // Debug logging
                    plugin.getLogger().info("Current version: " + currentVersion);
                    plugin.getLogger().info("Latest version: " + latestVersion);
                    plugin.getLogger().info("Parsed revisions: current=" + currentRev + ", latest=" + latestRev);

                    if (currentRev == -1 || latestRev == -1) {
                        plugin.getLogger().warning("Could not parse revision numbers properly.");
                    } else if (latestRev > currentRev) {
                        updateAvailable = true;
                        latestVersionString = latestVersion;
                        plugin.getLogger().warning("A new version is available: " + latestVersion + " (installed: " + currentVersion + ")");
                    } else {
                        plugin.getLogger().info("You are using the newest version.");
                    }
                }
            } catch (IOException e) {
                plugin.getLogger().warning("Can't get update information: " + e.getMessage());
            }
        });
    }

    private int extractRevision(String version) {
        if (version == null || !version.contains("rev-")) {
            return -1;
        }

        String[] parts = version.split("rev-");
        if (parts.length != 2) {
            return -1;
        }

        try {
            return Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            plugin.getLogger().warning("Invalid revision format: " + version);
            return -1;
        }
    }

    /**
     * Is update available boolean.
     *
     * @return the boolean
     */
    public boolean isUpdateAvailable() {

        return updateAvailable;
    }

    /**
     * Gets latest version string.
     *
     * @return the latest version string
     */
    public String getLatestVersionString() {
        return latestVersionString;
    }
}

package me.kofhwig.kofhCustomWelcomeMessage.joinevent;

import me.kofhwig.kofhCustomWelcomeMessage.KCFM;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinEvent implements Listener {

    private final KCFM plugin;
    // making the pattern
    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    public JoinEvent(KCFM plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        String playerName = event.getPlayer().getName();

        // Get welcome message from config safely
        String message = plugin.getConfig().getString("welcome-message", "#00ff7fWelcome %player%!")
                .replace("%player%", playerName);

        // Apply colors
        message = applyHexColors(message);
        message = ChatColor.translateAlternateColorCodes('&', message);

        // Send to joining player
        event.getPlayer().sendMessage(message);

        // Broadcast if enabled
        if (plugin.getConfig().getBoolean("active-broadcast", false)) {
            String broadcastMessage = plugin.getConfig()
                    .getString("broadcast-message", "#00ffff%player% joined the server!")
                    .replace("%player%", playerName);

            broadcastMessage = applyHexColors(broadcastMessage);
            broadcastMessage = ChatColor.translateAlternateColorCodes('&', broadcastMessage);

            Bukkit.getServer().broadcastMessage(broadcastMessage);
        }
    }

    private String applyHexColors(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group();
            try {
                matcher.appendReplacement(buffer, ChatColor.of(hex).toString());
            } catch (Exception e) {
                // If ChatColor.of() fails (old server version), leave hex as-is
                matcher.appendReplacement(buffer, hex);
            }
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }
}

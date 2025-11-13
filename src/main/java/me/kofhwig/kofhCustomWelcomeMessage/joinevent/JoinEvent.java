package me.kofhwig.kofhCustomWelcomeMessage.joinevent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoinEvent(PlayerJoinEvent playerJoinEvent){
        playerJoinEvent.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to the server " + playerJoinEvent.getPlayer().getName());
    }
}

package me.kofhwig.kofhCustomWelcomeMessage;

import me.kofhwig.kofhCustomWelcomeMessage.joinevent.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KofhCustomWelcomeMessage extends JavaPlugin {

    @Override
    public void onEnable() {
    getServer().getPluginManager().registerEvents(new JoinEvent(), this);

    }

    @Override
    public void onDisable() {


    }
}

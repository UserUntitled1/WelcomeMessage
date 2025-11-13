package me.kofhwig.kofhCustomWelcomeMessage;

import me.kofhwig.kofhCustomWelcomeMessage.joinevent.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KCFM extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

    }

    @Override
    public void onDisable() {


    }
}

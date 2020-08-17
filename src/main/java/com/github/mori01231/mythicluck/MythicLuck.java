package com.github.mori01231.mythicluck;

import org.bukkit.plugin.java.JavaPlugin;

public final class MythicLuck extends JavaPlugin {

    private static MythicLuck instance;

    public MythicLuck (){
        instance = this;
    }

    public static MythicLuck getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("MythicLuck has been enabled.");
        this.getCommand("mythicluckgive").setExecutor(new GiveCommandExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MythicLuck has been disabled.");
    }
}

package com.c010ur1355.unusable;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable(){
        getCommand("unusable").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}

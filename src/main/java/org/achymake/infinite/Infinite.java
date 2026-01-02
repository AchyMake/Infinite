package org.achymake.infinite;

import org.achymake.infinite.commands.*;
import org.achymake.infinite.data.*;
import org.achymake.infinite.handlers.*;
import org.achymake.infinite.listeners.*;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class Infinite extends JavaPlugin {
    private static Infinite instance;
    private Message message;
    private EntityHandler entityHandler;
    private MaterialHandler materialHandler;
    private RandomHandler randomHandler;
    private ScheduleHandler scheduleHandler;
    private WorldHandler worldHandler;
    private UpdateChecker updateChecker;
    private BukkitScheduler bukkitScheduler;
    private PluginManager pluginManager;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message();
        entityHandler = new EntityHandler();
        materialHandler = new MaterialHandler();
        randomHandler = new RandomHandler();
        scheduleHandler = new ScheduleHandler();
        worldHandler = new WorldHandler();
        updateChecker = new UpdateChecker();
        bukkitScheduler = getServer().getScheduler();
        pluginManager = getServer().getPluginManager();
        commands();
        events();
        reload();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getScheduleHandler().disable();
        sendInfo("Disabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    private void commands() {
        new InfiniteCommand();
    }
    private void events() {
        new EntityDeath();
        new PlayerFish();
        new PlayerInteractEntity();
        new PlayerItemConsume();
        new PlayerJoin();
        new PrepareAnvil();
    }
    public void reload() {
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else reloadConfig();
    }
    public PluginManager getPluginManager() {
        return pluginManager;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public RandomHandler getRandomHandler() {
        return randomHandler;
    }
    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }
    public EntityHandler getEntityHandler() {
        return entityHandler;
    }
    public Message getMessage() {
        return message;
    }
    public static Infinite getInstance() {
        return instance;
    }
    public NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(this, key);
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
}
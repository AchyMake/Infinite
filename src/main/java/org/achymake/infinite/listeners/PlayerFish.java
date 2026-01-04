package org.achymake.infinite.listeners;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.handlers.MaterialHandler;
import org.achymake.infinite.handlers.RandomHandler;
import org.achymake.infinite.handlers.WorldHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerFish implements Listener {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerFish() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getCaught() instanceof Item item) {
            var itemStackCaught = item.getItemStack();
            if (!getMaterialHandler().isEnable(itemStackCaught))return;
            var chance = getMaterialHandler().getChance(itemStackCaught);
            if (!getRandomHandler().isTrue(chance))return;
            var infiniteFood = getMaterialHandler().getInfiniteFood(itemStackCaught.getType());
            if (infiniteFood == null)return;
            if (getConfig().getBoolean("particle.enable")) {
                var particleType = getConfig().getString("particle.type");
                if (particleType == null)return;
                var count = getConfig().getInt("particle.count");
                var offsetX = getConfig().getDouble("particle.offsetX");
                var offsetY = getConfig().getDouble("particle.offsetY");
                var offsetZ = getConfig().getDouble("particle.offsetZ");
                var location = item.getLocation().add(0.0, 0.3, 0.0);
                getWorldHandler().spawnParticle(location, particleType, count, offsetX, offsetY, offsetZ);
            }
            if (getConfig().getBoolean("sound.enable")) {
                var soundType = getConfig().getString("sound.type");
                if (soundType == null)return;
                var volume = getConfig().getDouble("sound.volume");
                var pitch = getConfig().getDouble("sound.pitch");
                getWorldHandler().playSound(item, soundType, volume, pitch);
            }
            item.setItemStack(infiniteFood);
        }
    }
}
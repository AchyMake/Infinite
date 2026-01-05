package org.achymake.infinite.listeners;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.handlers.EntityHandler;
import org.achymake.infinite.handlers.MaterialHandler;
import org.achymake.infinite.handlers.RandomHandler;
import org.achymake.infinite.handlers.WorldHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

public class EntityDeath implements Listener {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private EntityHandler getEntityHandler() {
        return getInstance().getEntityHandler();
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
    public EntityDeath() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        var entity = event.getEntity();
        var entityType = event.getEntityType();
        if (!getEntityHandler().isEnable(entityType))return;
        if (entity.getKiller() == null)return;
        var chance = getEntityHandler().getChance(entityType);
        if (getConfig().getBoolean("looting-increase-chance")) {
            var heldItem = entity.getKiller().getInventory().getItemInMainHand();
            var looting = getMaterialHandler().getEnchantment("looting");
            if (heldItem.containsEnchantment(looting)) {
                var level = heldItem.getEnchantmentLevel(looting);
                if (!getRandomHandler().isTrue(chance * level))return;
            } else if (!getRandomHandler().isTrue(chance))return;
        } else if (!getRandomHandler().isTrue(chance))return;
        var itemStack = getEntityHandler().getInfiniteFood(entityType);
        if (itemStack == null)return;
        if (getConfig().getBoolean("particle.enable")) {
            var particleType = getConfig().getString("particle.type");
            if (particleType == null)return;
            var count = getConfig().getInt("particle.count");
            var offsetX = getConfig().getDouble("particle.offsetX");
            var offsetY = getConfig().getDouble("particle.offsetY");
            var offsetZ = getConfig().getDouble("particle.offsetZ");
            var location = entity.getLocation();
            getWorldHandler().spawnParticle(location, particleType, count, offsetX, offsetY, offsetZ);
        }
        if (getConfig().getBoolean("sound.enable")) {
            var soundType = getConfig().getString("sound.type");
            if (soundType == null)return;
            var volume = getConfig().getDouble("sound.volume");
            var pitch = getConfig().getDouble("sound.pitch");
            getWorldHandler().playSound(entity, soundType, volume, pitch);
        }
        event.getDrops().add(itemStack);
    }
}
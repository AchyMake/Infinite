package org.achymake.infinite.handlers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class WorldHandler {
    public Item spawnItem(Location location, ItemStack itemStack) {
        var world = location.getWorld();
        if (world != null) {
            var item = world.createEntity(location, Item.class);
            item.setItemStack(itemStack);
            world.addEntity(item);
            return item;
        } else return null;
    }
    public void spawnParticle(Location location, String particleType, int count, double offsetX, double offsetY, double offsetZ) {
        var world = location.getWorld();
        if (world == null)return;
        world.spawnParticle(Particle.valueOf(particleType), location, count, offsetX, offsetY, offsetZ, 0.0);
    }
    public void playSound(Entity entity, String soundType, double volume, double pitch) {
        var world = entity.getWorld();
        world.playSound(entity.getLocation(), Sound.valueOf(soundType), (float) volume, (float) pitch);
    }
}
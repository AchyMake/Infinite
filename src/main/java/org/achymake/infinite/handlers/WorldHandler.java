package org.achymake.infinite.handlers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;

public class WorldHandler {
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
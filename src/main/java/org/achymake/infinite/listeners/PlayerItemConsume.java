package org.achymake.infinite.listeners;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.handlers.MaterialHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.PluginManager;

public class PlayerItemConsume implements Listener {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerItemConsume() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (event.isCancelled())return;
        var itemStack = event.getItem();
        if (!getMaterialHandler().isInfinite(itemStack))return;
        var copy = getMaterialHandler().copy(itemStack, itemStack.getAmount());
        var equipmentSlot = event.getHand();
        var player = event.getPlayer();
        if (equipmentSlot.equals(EquipmentSlot.HAND)) {
            player.getInventory().setItemInMainHand(copy);
        } else if (equipmentSlot.equals(EquipmentSlot.OFF_HAND)) {
            player.getInventory().setItemInOffHand(copy);
        }
        var cooldown = getInstance().getConfig().getInt("cooldown");
        if (cooldown > 0) {
            player.setCooldown(copy.getType(), getInstance().getConfig().getInt("cooldown"));
        }
    }
}
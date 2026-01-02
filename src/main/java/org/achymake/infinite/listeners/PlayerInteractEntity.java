package org.achymake.infinite.listeners;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.handlers.EntityHandler;
import org.achymake.infinite.handlers.MaterialHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerInteractEntity implements Listener {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private EntityHandler getEntityHandler() {
        return getInstance().getEntityHandler();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerInteractEntity() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.isCancelled())return;
        if (!getEntityHandler().isTameable(event.getRightClicked()))return;
        var player = event.getPlayer();
        var itemStack = player.getInventory().getItemInMainHand();
        if (!getMaterialHandler().isInfinite(itemStack))return;
        event.setCancelled(true);
    }
}
package org.achymake.infinite.handlers;

import org.achymake.infinite.Infinite;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Tameable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class EntityHandler {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    public boolean isEnable(EntityType entityType) {
        return getConfig().getBoolean("entity-death." + entityType.name().toUpperCase() + ".enable");
    }
    public double getChance(EntityType entityType) {
        return getConfig().getDouble("entity-death." + entityType.name().toUpperCase() + ".chance");
    }
    public ItemStack getInfiniteFood(EntityType entityType) {
        if (isEnable(entityType)) {
            var section = getConfig().getConfigurationSection("entity-death." + entityType.name().toUpperCase() + ".item");
            if (section != null) {
                var itemStack = getMaterialHandler().getItemStack(section.getString("type"), section.getInt("amount"));
                if (itemStack != null) {
                    var itemMeta = itemStack.getItemMeta();
                    if (itemMeta != null) {
                        itemMeta.setItemName(getInstance().getMessage().addColor(section.getString("name")));
                        if (section.isList("lore")) {
                            var lore = new ArrayList<String>();
                            section.getStringList("lore").forEach(string -> lore.add(getInstance().getMessage().addColor(string)));
                            itemMeta.setLore(lore);
                        }
                        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
                        itemStack.setItemMeta(itemMeta);
                        return itemStack;
                    } else return null;
                } else return null;
            } else return null;
        } else return null;
    }
    public boolean isTameable(Entity entity) {
        return entity instanceof Tameable;
    }
}
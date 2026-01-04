package org.achymake.infinite.handlers;

import org.achymake.infinite.Infinite;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

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
        switch (entityType) {
            case CHICKEN -> {
                return getMaterialHandler().getInfiniteCookedChicken();
            }
            case COD -> {
                return getMaterialHandler().getInfiniteCookedCod();
            }
            case COW -> {
                return getMaterialHandler().getInfiniteSteak();
            }
            case PIG -> {
                return getMaterialHandler().getInfiniteCookedPorkchop();
            }
            case RABBIT -> {
                return getMaterialHandler().getInfiniteCookedRabbit();
            }
            case SALMON -> {
                return getMaterialHandler().getInfiniteCookedSalmon();
            }
            case SHEEP -> {
                return getMaterialHandler().getInfiniteCookedMutton();
            }
            default -> {
                return null;
            }
        }
    }
    public boolean isTameable(Entity entity) {
        return entity instanceof Tameable;
    }
}
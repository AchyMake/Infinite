package org.achymake.infinite.handlers;

import org.achymake.infinite.Infinite;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class MaterialHandler {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    public boolean isEnable(ItemStack itemStack) {
        return getConfig().getBoolean("fishing." + itemStack.getType().name().toUpperCase() + ".enable");
    }
    public double getChance(ItemStack itemStack) {
        return getConfig().getDouble("fishing." + itemStack.getType().name().toUpperCase() + ".chance");
    }
    public ItemStack getInfiniteFood(ItemStack itemStack) {
        if (isEnable(itemStack)) {
            var section = getConfig().getConfigurationSection("fishing." + itemStack.getType().name().toUpperCase() + ".item");
            if (section != null) {
                var infiniteItemStack = getInstance().getMaterialHandler().getItemStack(section.getString("type"), section.getInt("amount"));
                if (infiniteItemStack != null) {
                    var itemMeta = infiniteItemStack.getItemMeta();
                    if (itemMeta != null) {
                        itemMeta.setItemName(getInstance().getMessage().addColor(section.getString("name")));
                        if (section.isList("lore")) {
                            var lore = new ArrayList<String>();
                            section.getStringList("lore").forEach(string -> lore.add(getInstance().getMessage().addColor(string)));
                            itemMeta.setLore(lore);
                        }
                        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
                        infiniteItemStack.setItemMeta(itemMeta);
                        return infiniteItemStack;
                    } else return null;
                } else return null;
            } else return null;
        } else return null;
    }
    public Material get(String materialName) {
        return Material.getMaterial(materialName.toUpperCase());
    }
    public ItemStack getItemStack(String materialName, int amount) {
        var material = get(materialName);
        if (material != null) {
            return new ItemStack(material, amount);
        } else return null;
    }
    public ItemStack copy(ItemStack itemStack, int amount) {
        if (itemStack != null) {
            var copy = new ItemStack(itemStack);
            copy.setAmount(amount);
            return copy;
        } else return null;
    }
    public PersistentDataContainer getData(ItemStack itemStack) {
        var itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            return itemStack.getItemMeta().getPersistentDataContainer();
        } else return null;
    }
    public boolean isInfinite(ItemStack itemStack) {
        var data = getData(itemStack);
        if (data != null) {
            return data.has(getInstance().getNamespacedKey("infinite"));
        } else return false;
    }
}
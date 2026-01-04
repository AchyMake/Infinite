package org.achymake.infinite.handlers;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.data.Message;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    public boolean isEnable(ItemStack itemStack) {
        return getConfig().getBoolean("fishing." + itemStack.getType().name().toUpperCase() + ".enable");
    }
    public double getChance(ItemStack itemStack) {
        return getConfig().getDouble("fishing." + itemStack.getType().name().toUpperCase() + ".chance");
    }
    public void giveItemStack(Player player, ItemStack itemStack) {
        var rest = player.getInventory().addItem(itemStack);
        rest.values().forEach(itemStacks -> getWorldHandler().spawnItem(player.getLocation(), itemStacks));
    }
    public ItemStack getInfiniteFood(Material material) {
        switch (material) {
            case COD -> {
                return getInfiniteCookedCod();
            }
            case SALMON -> {
                return getInfiniteCookedSalmon();
            }
            default -> {
                return null;
            }
        }
    }
    public ItemStack getInfiniteSteak() {
        var itemStack = getItemStack("cooked_beef", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Steak"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedChicken() {
        var itemStack = getItemStack("cooked_chicken", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Chicken"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedCod() {
        var itemStack = getItemStack("cooked_cod", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Cod"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedMutton() {
        var itemStack = getItemStack("cooked_mutton", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Mutton"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedPorkchop() {
        var itemStack = getItemStack("cooked_porkchop", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Porkchop"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedRabbit() {
        var itemStack = getItemStack("cooked_rabbit", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Rabbit"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public ItemStack getInfiniteCookedSalmon() {
        var itemStack = getItemStack("cooked_salmon", 1);
        var itemMeta = itemStack.getItemMeta();
        itemMeta.setItemName(getMessage().addColor("&dInfinite Cooked Salmon"));
        var lore = new ArrayList<String>();
        lore.add(getMessage().addColor("&9Infinite Food"));
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(getInstance().getNamespacedKey("infinite"), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
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
    public enum food {
        cooked_beef,
        cooked_chicken,
        cooked_cod,
        cooked_mutton,
        cooked_porkchop,
        cooked_rabbit,
        cooked_salmon
    }
}
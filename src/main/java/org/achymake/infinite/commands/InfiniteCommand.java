package org.achymake.infinite.commands;

import org.achymake.infinite.Infinite;
import org.achymake.infinite.data.Message;
import org.achymake.infinite.handlers.MaterialHandler;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InfiniteCommand implements CommandExecutor, TabCompleter {
    private Infinite getInstance() {
        return Infinite.getInstance();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    public InfiniteCommand() {
        getInstance().getCommand("infinite").setExecutor(this);
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(getMessage().addColor("&6" + getInstance().name() + "&f: " + getInstance().version()));
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                getInstance().reload();
                player.sendMessage(getMessage().addColor("&6" + getInstance().name() + "&f: reloaded"));
                return true;
            } else if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                var target = getInstance().getServer().getPlayer(args[1]);
                if (target != null) {
                    if (args[2].equalsIgnoreCase("cooked_beef")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteSteak());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Steak"));
                    } else if (args[2].equalsIgnoreCase("cooked_chicken")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedChicken());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Chicken"));
                    } else if (args[2].equalsIgnoreCase("cooked_cod")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedCod());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Cod"));
                    } else if (args[2].equalsIgnoreCase("cooked_mutton")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedMutton());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Mutton"));
                    } else if (args[2].equalsIgnoreCase("cooked_porkchop")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedPorkchop());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Porkchop"));
                    } else if (args[2].equalsIgnoreCase("cooked_rabbit")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedRabbit());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Rabbit"));
                    } else if (args[2].equalsIgnoreCase("cooked_salmon")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedSalmon());
                        player.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Salmon"));
                    }
                    return true;
                }
            }
        } else if (sender instanceof ConsoleCommandSender consoleCommandSender) {
            if (args.length == 0) {
                consoleCommandSender.sendMessage(getInstance().name() + ": " + getInstance().version());
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                getInstance().reload();
                consoleCommandSender.sendMessage(getInstance().name() + ": reloaded");
                return true;
            } else if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                var target = getInstance().getServer().getPlayer(args[1]);
                if (target != null) {
                    if (args[2].equalsIgnoreCase("cooked_beef")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteSteak());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Steak"));
                    } else if (args[2].equalsIgnoreCase("cooked_chicken")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedChicken());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Chicken"));
                    } else if (args[2].equalsIgnoreCase("cooked_cod")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedCod());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Cod"));
                    } else if (args[2].equalsIgnoreCase("cooked_mutton")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedMutton());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Mutton"));
                    } else if (args[2].equalsIgnoreCase("cooked_porkchop")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedPorkchop());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Porkchop"));
                    } else if (args[2].equalsIgnoreCase("cooked_rabbit")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedRabbit());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Rabbit"));
                    } else if (args[2].equalsIgnoreCase("cooked_salmon")) {
                        getMaterialHandler().giveItemStack(target, getMaterialHandler().getInfiniteCookedSalmon());
                        consoleCommandSender.sendMessage(getMessage().addColor("&6You gave&f " + target.getName() + "&6 an&d Infinite Cooked Salmon"));
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        var commands = new ArrayList<String>();
        if (sender instanceof Player) {
            if (args.length == 1) {
                commands.add("reload");
                commands.add("give");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("give")) {
                    getInstance().getServer().getOnlinePlayers().forEach(target -> {
                        if (target.getName().startsWith(args[1])) {
                            commands.add(target.getName());
                        }
                    });
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    for (var food : MaterialHandler.food.values()) {
                        var name = food.name();
                        if (name.startsWith(args[2])) {
                            commands.add(name);
                        }
                    }
                }
            }
        }
        return commands;
    }
}
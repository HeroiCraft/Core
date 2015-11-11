package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CommandHead implements CommandExecutor {
  private final Core plugin;
  String skullName;
  public CommandHead(Core plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("head")) {
      if (args.length == 0 || args.length == 1) {
        if (sender.hasPermission("core.head.get")) {
          if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {skullName = (player.getName());} else {skullName = args[0];}
            player.getInventory().setItem(player.getInventory().firstEmpty(), setHead(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), skullName));
            player.sendMessage(ChatColor.GREEN + "Spawned " + skullName + "'s head!");
            return true;
          } else {
            sender.sendMessage(ChatColor.RED + "Please specify a player and target");
            return false;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to summon heads!");
        }
      } else if (args.length == 2) {
        if (sender.hasPermission("core.head.give")) {
          @SuppressWarnings("deprecation")
          Player target = plugin.getServer().getPlayer(args[1]);
          if (target == null) {
            sender.sendMessage(ChatColor.RED + args[1] + " is not online!");
            return true;
          } else {
            skullName = args[0];
            target.getInventory().setItem(target.getInventory().firstEmpty(), setHead(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), skullName));
            sender.sendMessage(ChatColor.GREEN + "Gave " + skullName + "'s head to " + target.getName() + "!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to give heads!");
          return true;
        }
      }
    }
    return false;
  }

  public ItemStack setHead(ItemStack item, String owner){
    SkullMeta data = (SkullMeta)item.getItemMeta();
    data.setOwner(owner);
    item.setItemMeta(data);
    return item;
  }
}

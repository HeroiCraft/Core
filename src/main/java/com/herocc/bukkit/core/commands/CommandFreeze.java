package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import com.herocc.bukkit.core.api.FreezeAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {
  private final Core plugin = Core.getPlugin();

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("freeze")) {
      if (sender.hasPermission("core.freeze")) {
        if (args.length == 1) {
          @SuppressWarnings("deprecation")
          Player player = plugin.getServer().getPlayer(args[0]);
          if (player != null) {
            toggleFreeze(sender, player.getName());
            return true;
          } else {
            sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "Please specify someone to freeze!");
          return true;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You don't have permission to freeze people!");
        return true;
      }
    }
    return false;
  }


  public void toggleFreeze(CommandSender sender, String name) {
    //Sets walk speed to 0 to freeze, onPlayerEvent is too resource intensive
    @SuppressWarnings("deprecation")
    Player player = plugin.getServer().getPlayer(name);
    FreezeAPI api = new FreezeAPI();
    if (player == null) {
      sender.sendMessage(ChatColor.RED + name + " is not online!");
    } else {
      if (FreezeAPI.frozen.contains(player.getUniqueId())) {
        api.unfreezePlayer(player);
        sender.sendMessage(ChatColor.GREEN + "Unfroze " + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + "!");
        player.sendMessage(ChatColor.GREEN + "You are now unfrozen!");
      } else {
        api.freezePlayer(player);
        sender.sendMessage(ChatColor.GREEN + "Froze " + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + "!");
        player.sendMessage(ChatColor.GREEN + "You were frozen by " + sender.getName() + "!");
      }
    }
  }
}

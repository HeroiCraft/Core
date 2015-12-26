package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import com.herocc.bukkit.core.api.SpeedAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpeed implements CommandExecutor {
  private final Core plugin;
  private final SpeedAPI api = new SpeedAPI();

  public CommandSpeed(Core plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length == 0) {
      if (sender instanceof Player) {
        if (sender.hasPermission("core.speed.self")) {
          Player player = (Player) sender;
          if (player.getWalkSpeed() != 0.2F || player.getFlySpeed() != 0.2F) {
            api.resetSpeed(player);
            player.sendMessage(ChatColor.GREEN + "Your Fly and Walk speeds have been reset");
            return true;
          } else {
            sender.sendMessage(ChatColor.RED + "Please specify a speed!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to change speeds!");
        }
      } else {
        sender.sendMessage(ChatColor.RED + "Silly console, you can't move!");
      }
    } else if (args.length == 1) {
      if (args[0].matches("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$")) { //Is it a non-negative float?
        if (sender.hasPermission("core.speed.self")) {
          if (sender instanceof Player) {
            Player player = (Player) sender;
            float speed = Float.parseFloat(args[0]);
            if (speed > 1){
              sender.sendMessage(ChatColor.RED + "Speed must be between 0 and 1");
              return true;
            }
            if (player.isFlying()) {
              player.setFlySpeed(speed);
              player.sendMessage(ChatColor.GREEN + "Flying at " + speed);
              return true;
            } else {
              player.setWalkSpeed(speed);
              player.sendMessage(ChatColor.GREEN + "Walking at " + speed);
              return true;
            }
          } else {
            sender.sendMessage(ChatColor.RED + "Please specify a player");
            return false;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to change speeds!");
        }
      } else {
        if (sender.hasPermission("core.speed.others")) {
          @SuppressWarnings("deprecation")
          Player player = plugin.getServer().getPlayer(args[0]);
          if (player == null) {
            sender.sendMessage(ChatColor.RED + args[0] + " isn't online right now!");
            return true;
          } else {
            api.resetSpeed(player);
            sender.sendMessage(ChatColor.GREEN + "Reset " + player.getDisplayName() + ChatColor.GREEN + "'s Walk and Fly Speed");
            player.sendMessage(ChatColor.GREEN + "Your Fly and Walk speeds have been reset!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to change other player's speeds!");
          return true;
        }
      }
    } else if (args.length == 2) {
      if (args[0].matches("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$")) {
        float speed = Float.parseFloat(args[0]);
        if (speed > 1){
          sender.sendMessage(ChatColor.RED + "Speed must be between 0 and 1");
          return true;
        }
        @SuppressWarnings("deprecation")
        Player player = plugin.getServer().getPlayer(args[1]);
        if (player == null){
          sender.sendMessage(ChatColor.RED + args[1] + " isn't online right now!");
          return true;
        } else {
          if (player.isFlying()){
            if (sender.hasPermission("core.speed.others.fly")) {
              player.setFlySpeed(speed);
              player.sendMessage(ChatColor.GREEN + "Your fly speed is now set to " + speed);
              sender.sendMessage(player.getDisplayName() + ChatColor.GREEN + "'s fly speed is now set to " + speed);
              return true;
            } else {
              sender.sendMessage(ChatColor.RED + "You don't have permission to change other's fly speed!");
              return true;
            }
          } else {
            if (sender.hasPermission("core.speed.others.walk")) {
              player.setWalkSpeed(speed);
              player.sendMessage(ChatColor.GREEN + "Your walk speed is now set to " + speed);
              sender.sendMessage(player.getDisplayName() + ChatColor.GREEN + "'s walk speed is now set to " + speed);
              return true;
            } else {
              sender.sendMessage(ChatColor.RED + "You don't have permission to change other's walk speed!");
              return true;
            }
          }
        }
      } else {
        sender.sendMessage(ChatColor.RED + "Invalid speed!");
        return false;
      }
    } else {
      sender.sendMessage(ChatColor.RED + "Invalid number of arguments!");
      return false;
    }
    return false;
  }
}


package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {
  private final Core plugin = Core.getPlugin();

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("fly")) {
      if (args.length == 0){
        if (sender.hasPermission("core.fly.self")) {
          if (sender instanceof Player) {
            Player player = (Player) sender;
            toggleFly(sender, player.getName());
            return true;
          } else {
            sender.sendMessage(ChatColor.RED + "Silly console, you can't fly!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to toggle fly!");
        }
      } else if (args.length == 1){
        if (sender.hasPermission("core.fly.others")){
          @SuppressWarnings("deprecation")
          Player player = plugin.getServer().getPlayer(args[0]);
          if (player != null) {
            toggleFly(sender, player.getName());
          } else {
            sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
          }
          return true;
        } else {
          sender.sendMessage(ChatColor.RED + "You don't have permission to toggle fly for others!");
          return true;
        }
      }
    }
    return false;
  }

  public void toggleFly(CommandSender sender, String name) {
    @SuppressWarnings("deprecation")
    Player player = plugin.getServer().getPlayer(name);
    if (player == null) {
      //This should not be triggered under ordinary conditions
      //Protections should be implemented where this function is used
      sender.sendMessage(ChatColor.RED + name + " is not online!");
      return;
    }
    if (player.getAllowFlight()){
      player.setAllowFlight(false);
      //player.teleport(new Location(player.getWorld(), player.getLocation().getBlockX(), player.getWorld().getHighestBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockZ()).getY(), player.getLocation().getBlockZ())); //TODO Find a better implementation of this
      player.setFlying(false);
      player.sendMessage(ChatColor.GREEN + "Toggled your fly mode " + ChatColor.RED + "OFF");
      if (!player.getName().equalsIgnoreCase(sender.getName())) {
        sender.sendMessage(ChatColor.GREEN + "Toggled " + player.getDisplayName() + ChatColor.GREEN + "'s fly OFF");
      }
    } else {
      player.setAllowFlight(true);
      player.sendMessage(ChatColor.GREEN + "Toggled your fly mode " + ChatColor.DARK_GREEN + "ON");
      if (!player.getName().equalsIgnoreCase(sender.getName())) {
        sender.sendMessage(ChatColor.GREEN + "Toggled " + player.getDisplayName() + ChatColor.GREEN + "'s fly ON");
      }
    }
  }
}

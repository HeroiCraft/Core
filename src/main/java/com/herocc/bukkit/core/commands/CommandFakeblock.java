package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Set;

public class CommandFakeblock implements CommandExecutor {
  private final Core plugin = Core.getPlugin();
  String doubleRegex = "\"[-+]?[0-9]*\\\\.?[0-9]+([eE][-+]?[0-9]+)?\"";

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("fakeblock")) {
      if (sender.hasPermission("core.fake")) {
        Location loc;
        if (args.length == 1 || args.length == 4){
          if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 1) {
              loc = player.getTargetBlock((Set<Material>) null, 15).getLocation();
            } else {
              loc = toLocation(player.getWorld().toString(), args[1], args[2], args[3]);
            }
          } else {
            sender.sendMessage(ChatColor.RED + "Please specify a world!");
            return false;
          }
        } else if (args.length == 5){
          loc = toLocation(args[4], args[1], args[2], args[3]);
        } else {
          sender.sendMessage(ChatColor.RED + "Invalid number of arguments!");
          return false;
        }
        if (Material.getMaterial(args[0]) != null) {
          setFakeBlock(Material.getMaterial(args[0]), loc);
          sender.sendMessage(ChatColor.GREEN + "Changed Block at X" + loc.getBlockX() + " Y" + loc.getBlockY() + " Z" + loc.getBlockZ() + " to " + Material.getMaterial(args[0]).toString());
          return true;
        } else {
          sender.sendMessage(ChatColor.RED + "Invalid material: " + args[0]);
          return false;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You don't have permission to set Fake Blocks!");
        return false;
      }
    }
    return false;
  }

  public void setFakeBlock(Material mat, Location loc){
    for (Entity e : loc.getChunk().getEntities()) {
      if (e instanceof Player) {
        setFakeBlock(mat, loc, (Player) e);
      }
    }
  }

  @SuppressWarnings("deprecation")
  public void setFakeBlock(Material mat, Location loc, Player player){
    player.sendBlockChange(loc, mat, (byte) 0);
  }

  public Location toLocation(String world, String x, String y, String z) {
    if (isLocation(world, x, y, z)) {
      Double xCord = Double.parseDouble(x);
      Double yCord = Double.parseDouble(y);
      Double zCord = Double.parseDouble(z);
      return new Location(plugin.getServer().getWorld(world), xCord, yCord, zCord);
    }
    return null;
  }

  public boolean isLocation(String world, String x, String y, String z) {
    return (x.matches(doubleRegex) && y.matches(doubleRegex) && z.matches(doubleRegex) && plugin.getServer().getWorld(world) != null);
  }

}

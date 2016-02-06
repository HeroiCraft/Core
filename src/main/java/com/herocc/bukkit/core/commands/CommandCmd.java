package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public class CommandCmd implements CommandExecutor {
  private final Core plugin = Core.getPlugin();

  String pluginName;
  String pluginVersion;
  String commandUsage;
  String commandPermission;
  String commandDescription;
  Command targetCommand;

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("cmd")) {
      if (sender.hasPermission("core.cmd")) {
        if (args.length == 1) {
          String command = args[0];
          if (isBuiltIn(command)){
            pluginName = "Builtin Command";
            pluginVersion = plugin.getServer().getVersion();
          } else {
            PluginCommand pluginCommand = plugin.getServer().getPluginCommand(command);
            pluginName = pluginCommand.getPlugin().getName();
            pluginVersion = pluginCommand.getPlugin().getDescription().getVersion();
          }
          targetCommand = plugin.getCommand(command);
          if (targetCommand != null) {
            if (targetCommand.getUsage() != null) {
              commandUsage = targetCommand.getUsage();
            }
            if (targetCommand.getPermission() != null) {
              commandPermission = targetCommand.getPermission();
            }
            if (targetCommand.getDescription() != null) {
              commandDescription = targetCommand.getDescription();
            }
          }
          sendCommandInfo(sender, command);
          return true;
        } else {
          sender.sendMessage(ChatColor.RED + "Invalid number of arguments");
          return true;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You don't have permission to see info about commands!");
        return true;
      }
    }
    return false;
  }

  public void sendCommandInfo(CommandSender sender, String command){
    sender.sendMessage(ChatColor.GREEN + "Plugin: " + pluginName + ", version: " + pluginVersion);
    if (commandUsage != null && commandUsage.contains("<command>")) {
      commandUsage = commandUsage.replaceFirst("<command>", command);
      sender.sendMessage(ChatColor.GREEN + "Usage: " + commandUsage);
    }
    if (commandPermission != null && !commandPermission.isEmpty()) {
      sender.sendMessage(ChatColor.GREEN + "Permission: " + commandPermission);
    }
    if (commandDescription != null && !commandDescription.isEmpty()){
      sender.sendMessage(ChatColor.GREEN + "Description: " + commandDescription);
    }
  }

  private boolean isBuiltIn(String command) {
    return (plugin.getServer().getPluginCommand(command) == null);
  }
}

package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public class CommandCmd implements CommandExecutor {
  private final Core plugin;
  private static boolean isBuiltIn = false;

  public CommandCmd(Core plugin) {
    this.plugin = plugin;
  }

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
          PluginCommand pluginCommand = plugin.getServer().getPluginCommand(command);
          if (pluginCommand == null){
            Command builtinCommand = plugin.getCommand(command);
            if (builtinCommand == null) {
              sender.sendMessage(ChatColor.RED + "That command doesn't seem to exist!");
              return false;
            } else {
              targetCommand = builtinCommand;
              isBuiltIn = true;
            }
          } else {
            targetCommand = pluginCommand;
            isBuiltIn = false;
          }

          if (isBuiltIn){
            pluginName = "Builtin Command";
            pluginVersion = plugin.getServer().getVersion();
          } else {
            pluginName = pluginCommand.getPlugin().getName();
            pluginVersion = pluginCommand.getPlugin().getDescription().getVersion();
          }
          commandUsage = targetCommand.getUsage();
          commandPermission = targetCommand.getPermission();
          commandDescription = targetCommand.getDescription();
          sendCommandInfo(sender);
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

  public void sendCommandInfo(CommandSender sender){
    sender.sendMessage(ChatColor.GREEN + "Plugin: " + pluginName + " version: " + pluginVersion);
    sender.sendMessage(ChatColor.GREEN + "Usage: " + commandUsage);
    if (commandPermission != null) {
      sender.sendMessage(ChatColor.GREEN + "Permission: " + commandPermission);
    }
    if (commandDescription != null){
      sender.sendMessage(ChatColor.GREEN + "Description: " + commandDescription);
    }
  }
}

package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public class CommandCmd implements CommandExecutor {
  private final Core plugin;

  public CommandCmd(Core plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("cmd")) {
      if (sender.hasPermission("core.cmd")) {
        if (args.length == 1) {
          String command = args[0];
          PluginCommand targetCommand = plugin.getCommand(command);

          if (targetCommand == null) {
            sender.sendMessage(ChatColor.RED + "Can't find a plugin that provides " + command);
            return true;
          } else {
            String pluginName = targetCommand.getPlugin().getName();
            String pluginVersion = targetCommand.getPlugin().getDescription().getVersion();
            String commandUsage = targetCommand.getUsage();
            String commandPermission = targetCommand.getPermission();

            sender.sendMessage(ChatColor.GREEN + "Plugin: " + pluginName + " version: " + pluginVersion);
            sender.sendMessage(ChatColor.GREEN + "Usage: " + commandUsage);
            if (commandPermission != null) {
              sender.sendMessage(ChatColor.GREEN + "Permission: " + commandPermission);
            }
            return true;
          }
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
}

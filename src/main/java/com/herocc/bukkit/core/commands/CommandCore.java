package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCore implements CommandExecutor {
  private final Core plugin = Core.getPlugin();

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("core")) {
      sender.sendMessage(ChatColor.GREEN + "Core version: " + plugin.getDescription().getVersion() + "!");
      return true;
    }
    return false;
  }
}

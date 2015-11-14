package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import com.herocc.bukkit.core.util.Reference;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCore implements CommandExecutor {
  private final Core plugin;
  public CommandCore(Core plugin) { this.plugin = plugin; }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("core")) {
      sender.sendMessage(ChatColor.GREEN + "Core version: " + Reference.VERSION + "!");
    }
    return false;
  }
}

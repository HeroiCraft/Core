package com.herocc.bukkit.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.herocc.bukkit.core.Core;

public class CommandFakeblock implements CommandExecutor {
  private final Core plugin = Core.getPlugin();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("fakeblock")) {
    sender.sendMessage("Testing..");
  }

}

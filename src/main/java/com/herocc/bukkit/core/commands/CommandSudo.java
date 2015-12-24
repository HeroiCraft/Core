package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSudo implements CommandExecutor {
  private final Core plugin;

  public CommandSudo(Core plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("sudo")) {
      if (sender.hasPermission("core.sudo")) {
        if (args.length >= 2) {
          String input = "";
          @SuppressWarnings("deprecation")
          Player subject = plugin.getServer().getPlayer(args[0]);
          for (int i = 1; i < args.length; i++) {
            input = input + args[i] + " ";
          }
          input = input.trim();
          boolean isCommand = input.startsWith("/");
          if (subject == null) {
            sender.sendMessage(ChatColor.RED + args[0] + " isn't online right now!");
            return true;
          } else {
            if (isCommand) {
              input = input.substring(1);
              plugin.getServer().dispatchCommand(subject, input);
              sender.sendMessage(ChatColor.GREEN + "Successfully made " + subject.getDisplayName() + ChatColor.GREEN + " execute " + ChatColor.RESET + input);
              return true;
            } else {
              subject.chat(input);
              sender.sendMessage(ChatColor.GREEN + "Successfully made " + subject.getDisplayName() + ChatColor.GREEN + " say " + ChatColor.RESET + input);
              return true;
            }
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You must specify a Player and Input");
          return true;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use Sudo!");
        return true;
      }
    }
    return false;
  }
}

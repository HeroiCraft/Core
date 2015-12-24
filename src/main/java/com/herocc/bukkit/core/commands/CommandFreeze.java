package com.herocc.bukkit.core.commands;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandFreeze implements CommandExecutor {
  private final Core plugin;

  public CommandFreeze(Core plugin) { this.plugin = plugin; }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("freeze")) {
      if (sender.hasPermission("core.freeze")) {
        if (args.length == 1) {
          @SuppressWarnings("deprecation")
          Player player = plugin.getServer().getPlayer(args[0]);
          if (player != null) {
            toggleFreeze(sender, player.getName());
            return true;
          } else {
            sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
            return true;
          }
        } else {
          sender.sendMessage(ChatColor.RED + "Please specify someone to freeze!");
          return true;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You don't have permission to freeze people!");
        return true;
      }
    }
    return false;
  }

  public List<UUID> frozen = new ArrayList<>();
  public void toggleFreeze(CommandSender sender, String name) {
    //Sets walk speed to 0 to freeze, onPlayerEvent is too resource intensive
    @SuppressWarnings("deprecation")
    Player player = plugin.getServer().getPlayer(name);
    PotionEffect noJump = PotionEffectType.JUMP.createEffect(999999, 128); // 128 = -1, prevents jumping
    if (player == null) {
      sender.sendMessage(ChatColor.RED + name + " is not online!");
    } else {
      if (frozen.contains(player.getUniqueId())) {
        player.setWalkSpeed(0.2F);
        player.removePotionEffect(PotionEffectType.JUMP);
        frozen.remove(player.getUniqueId());
        sender.sendMessage(ChatColor.DARK_GREEN + "Unfroze " + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + "!");
        player.sendMessage(ChatColor.GREEN + "You are now unfrozen!");
      } else {
        player.setWalkSpeed(0);
        player.addPotionEffect(noJump);
        frozen.add(player.getUniqueId());
        sender.sendMessage(ChatColor.DARK_GREEN + "Froze " + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + "!");
        player.sendMessage(ChatColor.GREEN + "You were frozen by " + sender.getName() + "!");
      }
    }
  }
}

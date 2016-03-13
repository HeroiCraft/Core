package com.herocc.bukkit.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.herocc.bukkit.core.Core;

public class CommandFakeblock implements CommandExecutor {
	private final Core plugin = Core.getPlugin();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		if (cmd.getName().equalsIgnoreCase("fakeblock")) {
	        if (sender.hasPermission("core.head.get")) {
	        	Player player = (Player) sender;
	        	player.sendBlockChange(player.getLocation(), Material.WOOL, (byte) 0);
	        	sender.sendMessage(ChatColor.GREEN + "The block has changed!");
	        } else {
	        	sender.sendMessage(ChatColor.RED + "Sorry, You do not have permission to use this command!");
	        }
		}
		return false;
	}

}

package com.herocc.bukkit.core;

import com.herocc.bukkit.core.commands.CommandHead;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("head").setExecutor(new CommandHead(this));
  }

  @Override
  public void onDisable() {

  }
}

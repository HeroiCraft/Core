package com.herocc.bukkit.core;

import com.herocc.bukkit.core.commands.CommandFly;
import com.herocc.bukkit.core.commands.CommandHead;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("head").setExecutor(new CommandHead(this));
    this.getCommand("fly").setExecutor(new CommandFly(this));
  }

  @Override
  public void onDisable() {

  }
}

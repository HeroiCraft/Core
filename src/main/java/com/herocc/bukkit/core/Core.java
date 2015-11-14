package com.herocc.bukkit.core;

import com.herocc.bukkit.core.commands.CommandCore;
import com.herocc.bukkit.core.commands.CommandFly;
import com.herocc.bukkit.core.commands.CommandFreeze;
import com.herocc.bukkit.core.commands.CommandHead;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getCommand("core").setExecutor(new CommandCore(this));
    this.getCommand("head").setExecutor(new CommandHead(this));
    this.getCommand("fly").setExecutor(new CommandFly(this));
    this.getCommand("freeze").setExecutor(new CommandFreeze(this));
  }

  @Override
  public void onDisable() {

  }
}

package com.herocc.bukkit.core;

import com.herocc.bukkit.core.api.FreezeAPI;
import com.herocc.bukkit.core.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
  FreezeAPI freeze = new FreezeAPI(this);

  @Override
  public void onEnable() {
    this.getCommand("core").setExecutor(new CommandCore(this));
    this.getCommand("head").setExecutor(new CommandHead(this));
    this.getCommand("fly").setExecutor(new CommandFly(this));
    this.getCommand("freeze").setExecutor(new CommandFreeze(this));
    this.getCommand("sudo").setExecutor(new CommandSudo(this));
    this.getCommand("speed").setExecutor(new CommandSpeed(this));
  }

  @Override
  public void onDisable() {
    freeze.unfreezeAll(false);
  }
}

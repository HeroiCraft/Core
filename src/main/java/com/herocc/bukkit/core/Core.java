package com.herocc.bukkit.core;

import com.herocc.bukkit.core.api.FreezeAPI;
import com.herocc.bukkit.core.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Core extends JavaPlugin {
  public final static Logger log = Logger.getLogger("Minecraft");
  public final static String logPrefix = "[Core] ";
  FreezeAPI freeze = new FreezeAPI();
  private static Core instance;

  @Override
  public void onEnable() {
    instance = this;
    log.info(logPrefix + "v. " + this.getDescription().getVersion() + " enabled!");
    this.getCommand("core").setExecutor(new CommandCore());
    this.getCommand("head").setExecutor(new CommandHead());
    this.getCommand("fly").setExecutor(new CommandFly());
    this.getCommand("freeze").setExecutor(new CommandFreeze());
    this.getCommand("sudo").setExecutor(new CommandSudo());
    this.getCommand("speed").setExecutor(new CommandSpeed());
    this.getCommand("cmd").setExecutor(new CommandCmd());
    this.getCommand("fakeblock").setExecutor(new CommandFakeblock());
  }

  @Override
  public void onDisable() {
    instance = null;
    freeze.unfreezeAll(false);
  }

  public static final Core getPlugin() {
    return instance;
  }
}

package com.herocc.bukkit.core;

import java.util.logging.Logger;

import com.herocc.bukkit.core.api.FreezeAPI;
import com.herocc.bukkit.core.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
  public final static Logger log = Logger.getLogger("Minecraft");
  public final static String logPrefix = "[HeroiCraft Core] ";
  FreezeAPI freeze = new FreezeAPI(this);

  @Override
  public void onEnable() {
    log.info(logPrefix + " Plugin Version " + this.getDescription().getVersion() + " enabled!");
    this.getCommand("core").setExecutor(new CommandCore(this));
    this.getCommand("head").setExecutor(new CommandHead(this));
    this.getCommand("fly").setExecutor(new CommandFly(this));
    this.getCommand("freeze").setExecutor(new CommandFreeze(this));
    this.getCommand("sudo").setExecutor(new CommandSudo(this));
    this.getCommand("speed").setExecutor(new CommandSpeed(this));
    this.getCommand("cmd").setExecutor(new CommandCmd(this));
  }

  @Override
  public void onDisable() {
    log.info(logPrefix + " Plugin Version " + this.getDescription().getVersion() + " disabled!");
    freeze.unfreezeAll(false);
  }
}

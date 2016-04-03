package com.herocc.bukkit.core.util;

import com.herocc.bukkit.core.Core;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

  public static YamlConfiguration getConfigFile(String fileName, boolean isInDataFolder){
    final Core plugin = Core.getPlugin();
    if (isInDataFolder) {
      File file = new File(plugin.getDataFolder(), fileName);
      return YamlConfiguration.loadConfiguration(file);
    } else {
      return YamlConfiguration.loadConfiguration(Core.class.getResourceAsStream(fileName));
    }
  }
}

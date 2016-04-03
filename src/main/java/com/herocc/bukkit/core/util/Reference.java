package com.herocc.bukkit.core.util;

import com.herocc.bukkit.core.Core;

public class Reference {
  public static final String BUILDTIME = Config.getConfigFile("plugin.yml", false).getString("BuildTime");
  public static final String VERSION = Core.getPlugin().getDescription().getVersion();
}

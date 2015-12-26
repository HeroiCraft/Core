package com.herocc.bukkit.core.api;

import org.bukkit.entity.Player;

public class SpeedAPI {
  public void resetSpeed(Player player){
    player.setFlySpeed(0.2F);
    player.setWalkSpeed(0.2F);
    }
}

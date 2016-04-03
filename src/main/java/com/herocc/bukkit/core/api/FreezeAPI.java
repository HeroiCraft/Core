package com.herocc.bukkit.core.api;

import com.herocc.bukkit.core.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FreezeAPI {
  private final Core plugin = Core.getPlugin();

  PotionEffect noJump = PotionEffectType.JUMP.createEffect(999999, 128); // 128 = -1, prevents jumping
  public static List<UUID> frozen = new ArrayList<>();

  public void unfreezePlayer(Player player){
    player.setWalkSpeed(0.2F);
    player.removePotionEffect(PotionEffectType.JUMP);
    frozen.remove(player.getUniqueId());
  }

  public void freezePlayer(Player player){
    player.setWalkSpeed(0);
    player.addPotionEffect(noJump);
    frozen.add(player.getUniqueId());
  }

  public void unfreezeAll(Boolean alert) {
    if (frozen.size() != 0) {
      int unfrozenPlayers = 0;
      plugin.getLogger().info("Unfreezing all players...");
      for (UUID uuid : frozen) {
        unfrozenPlayers++;
        Player player = plugin.getServer().getPlayer(uuid);
        unfreezePlayer(player);
        Core.log.fine(Core.logPrefix + "Unfroze " + player.getDisplayName());
        if (alert) {
          player.sendMessage(ChatColor.GREEN + "All players are now unfrozen!");
        }
      }
      Core.log.info(Core.logPrefix + unfrozenPlayers + " players unfrozen!");
    }
  }
}

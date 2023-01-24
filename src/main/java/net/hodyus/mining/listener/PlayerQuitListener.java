package net.hodyus.mining.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerQuitListener implements Listener {

    @EventHandler
    void on(PlayerQuitEvent event) {

        if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING))
            event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);

    }
}

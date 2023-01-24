package net.hodyus.mining.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WorldChangeListener implements Listener {

    @EventHandler
    void on(PlayerChangedWorldEvent event) {

        Player player = event.getPlayer();

        if (event.getFrom().getName().equals("mining"))
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);

        else if (player.getWorld().getName().equals("mining"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));

    }

}

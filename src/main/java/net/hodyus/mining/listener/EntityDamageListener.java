package net.hodyus.mining.listener;

import net.hodyus.mining.MiningConstants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    void on(EntityDamageEvent event) {

        if (!event.getEntity().getWorld().getName().equals(MiningConstants.MINING_WORLD)) return;

        event.setCancelled(true);
        event.setDamage(0.0);

    }

}

package net.hodyus.mining.listener;

import lombok.RequiredArgsConstructor;
import net.hodyus.mining.MiningConstants;
import net.hodyus.mining.model.Ore;
import net.hodyus.mining.registry.OreRegistry;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final OreRegistry registry;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    void on(BlockBreakEvent event) {

        if (!event.getBlock().getWorld().getName().equals(MiningConstants.MINING_WORLD)) return;

        if (event.getBlock().getType() != Material.STONE) return;

        event.getBlock().setType(Material.AIR);

        Player player = event.getPlayer();

        for (Ore ore : registry.getOres()) {

            if ((MiningConstants.STATIC_RANDOM.nextDouble() * 100) > ore.getChance()) continue;

            if (ore.getMinY() < event.getBlock().getLocation().getY()) continue;

            ItemStack itemInHand = player.getItemInHand(),
                    oreDrop = ore.getItemStack().clone();

            if (ore.isDoesFortuneApply())
                oreDrop.setAmount(MiningConstants.STATIC_RANDOM.nextInt(itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1);

            player.giveExp(oreDrop.getAmount());

            if (player.getInventory().firstEmpty() != -1) player.getInventory().addItem(oreDrop);
            else player.getWorld().dropItem(event.getBlock().getLocation(), oreDrop);

            break;

        }

    }

}

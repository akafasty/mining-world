package net.hodyus.mining;

import net.hodyus.mining.command.MineCommand;
import net.hodyus.mining.listener.BlockBreakListener;
import net.hodyus.mining.listener.EntityDamageListener;
import net.hodyus.mining.listener.PlayerQuitListener;
import net.hodyus.mining.listener.WorldChangeListener;
import net.hodyus.mining.model.Ore;
import net.hodyus.mining.registry.OreRegistry;
import net.hodyus.mining.world.MiningWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MiningPlugin extends JavaPlugin {

    private final OreRegistry registry = new OreRegistry();

    public static MiningPlugin getInstance() {
        return JavaPlugin.getPlugin(MiningPlugin.class);
    }

    public void onLoad() {

        registerOres();

    }

    public void onEnable() {

        MiningWorld.createMiningWorld();

        new MineCommand();

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(registry), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), this);

    }

    private void registerOres() {

        registry.register(
                Ore.builder().chance(4.3).minY(100).doesFortuneApply(true).itemStack(new ItemStack(Material.COAL, 1)).build()
        );

        registry.register(
                Ore.builder().chance(3.7).minY(25).doesFortuneApply(true).itemStack(new ItemStack(Material.INK_SACK, 3, (short) 4)).build()
        );

        registry.register(
                Ore.builder().chance(3.0).minY(25).doesFortuneApply(true).itemStack(new ItemStack(Material.REDSTONE, 3)).build()
        );

        registry.register(
                Ore.builder().chance(2.1).minY(100).doesFortuneApply(false).itemStack(new ItemStack(Material.IRON_ORE)).build()
        );

        registry.register(
                Ore.builder().chance(1.5).minY(100).doesFortuneApply(false).itemStack(new ItemStack(Material.GOLD_ORE)).build()
        );

        registry.register(
                Ore.builder().chance(0.8).minY(20).doesFortuneApply(true).itemStack(new ItemStack(Material.DIAMOND)).build()
        );

        registry.register(
                Ore.builder().chance(0.5).minY(20).doesFortuneApply(true).itemStack(new ItemStack(Material.EMERALD)).build()
        );

    }

}

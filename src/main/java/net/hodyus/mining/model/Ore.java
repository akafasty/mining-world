package net.hodyus.mining.model;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Builder @Getter
public class Ore {

    private final double chance;
    private final ItemStack itemStack;
    private final boolean doesFortuneApply;
    private final int minY;

}

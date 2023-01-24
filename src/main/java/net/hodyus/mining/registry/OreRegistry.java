package net.hodyus.mining.registry;

import com.google.common.collect.Sets;
import net.hodyus.mining.model.Ore;

import java.util.Set;

public class OreRegistry {

    private final Set<Ore> REGISTRY = Sets.newHashSetWithExpectedSize(6);

    public void register(Ore ore) {
        REGISTRY.add(ore);
    }

    public Set<Ore> getOres() {
        return REGISTRY;
    }

}

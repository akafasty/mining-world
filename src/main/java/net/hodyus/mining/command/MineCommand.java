package net.hodyus.mining.command;

import net.hodyus.commons.command.CustomCommand;
import net.hodyus.commons.util.CustomActionBar;
import net.hodyus.mining.MiningConstants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class MineCommand extends CustomCommand {

    public MineCommand() {

        super("mine", null, true, "mina");

        CraftServer craftServer = (CraftServer) Bukkit.getServer();
        SimpleCommandMap simpleCommandMap = craftServer.getCommandMap();

        simpleCommandMap.register("mining", this);

    }

    public void onCommand(CommandSender sender, String[] strings) {

        Player player = (Player) sender;

        if (player.getWorld().getName().equals("mining")) {

            player.sendMessage("§cVocê já está no mundo de mineração!");
            return;

        }

        World world = Bukkit.getWorld(MiningConstants.MINING_WORLD);
        int x = ThreadLocalRandom.current().nextInt(600),
                z = ThreadLocalRandom.current().nextInt(600),
                y = world.getHighestBlockYAt(x, z);

        player.teleport(new Location(world, x, y, z));

        CustomActionBar.send(player, "§eBem-vindo ao mundo de Mineração!");

    }
}

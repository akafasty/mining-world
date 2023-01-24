package net.hodyus.mining.world;

import lombok.experimental.UtilityClass;
import net.hodyus.mining.MiningConstants;
import net.hodyus.mining.MiningPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import static net.hodyus.mining.MiningConstants.MINING_WORLD;

@UtilityClass
public class MiningWorld {

    public void createMiningWorld() {

        Bukkit.unloadWorld(MINING_WORLD, false);
        Path.of(MINING_WORLD).toFile().delete();

        World miningWorld = new WorldCreator(MINING_WORLD).generatorSettings("{\"coordinateScale\":684.412,\"heightScale\":684.412,\"lowerLimitScale\":512.0,\"upperLimitScale\":512.0,\"depthNoiseScaleX\":200.0,\"depthNoiseScaleZ\":200.0,\"depthNoiseScaleExponent\":0.5,\"mainNoiseScaleX\":80.0,\"mainNoiseScaleY\":160.0,\"mainNoiseScaleZ\":80.0,\"baseSize\":8.5,\"stretchY\":12.0,\"biomeDepthWeight\":0.2,\"biomeDepthOffset\":0.0,\"biomeScaleWeight\":1.0,\"biomeScaleOffset\":0.0,\"seaLevel\":63,\"useCaves\":false,\"useDungeons\":false,\"dungeonChance\":8,\"useStrongholds\":false,\"useVillages\":false,\"useMineShafts\":false,\"useTemples\":false,\"useMonuments\":false,\"useRavines\":false,\"useWaterLakes\":false,\"waterLakeChance\":4,\"useLavaLakes\":false,\"lavaLakeChance\":80,\"useLavaOceans\":false,\"fixedBiome\":-1,\"biomeSize\":4,\"riverSize\":4,\"dirtSize\":33,\"dirtCount\":10,\"dirtMinHeight\":0,\"dirtMaxHeight\":256,\"gravelSize\":33,\"gravelCount\":8,\"gravelMinHeight\":0,\"gravelMaxHeight\":256,\"graniteSize\":33,\"graniteCount\":10,\"graniteMinHeight\":0,\"graniteMaxHeight\":80,\"dioriteSize\":33,\"dioriteCount\":10,\"dioriteMinHeight\":0,\"dioriteMaxHeight\":80,\"andesiteSize\":33,\"andesiteCount\":10,\"andesiteMinHeight\":0,\"andesiteMaxHeight\":80,\"coalSize\":1,\"coalCount\":0,\"coalMinHeight\":0,\"coalMaxHeight\":0,\"ironSize\":1,\"ironCount\":0,\"ironMinHeight\":0,\"ironMaxHeight\":0,\"goldSize\":1,\"goldCount\":0,\"goldMinHeight\":0,\"goldMaxHeight\":0,\"redstoneSize\":1,\"redstoneCount\":0,\"redstoneMinHeight\":0,\"redstoneMaxHeight\":0,\"diamondSize\":1,\"diamondCount\":0,\"diamondMinHeight\":0,\"diamondMaxHeight\":0,\"lapisSize\":1,\"lapisCount\":0,\"lapisCenterHeight\":0,\"lapisSpread\":0,\"emeraldSize\":1,\"emeraldCount\":0,\"emeraldCenterHeight\":0,\"emeraldSpread\":0}").createWorld();

        miningWorld.setTicksPerAnimalSpawns(Integer.MAX_VALUE);
        miningWorld.setTicksPerMonsterSpawns(Integer.MAX_VALUE);

        miningWorld.setAutoSave(false);
        miningWorld.setPVP(false);

        miningWorld.setGameRuleValue("randomTickSpeed", "1");

    }

    private void copyFileStructure(File source, File target) {

        try {

            List<String> ignore = Arrays.asList("uid.dat", "session.lock");

            if(!ignore.contains(source.getName())) {

                if(source.isDirectory()) {

                    if(!target.exists())
                        if (!target.mkdirs()) throw new IOException("Couldn't create world directory!");


                    String[] files = source.list();

                    for (String file : files) {

                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);

                        copyFileStructure(srcFile, destFile);
                    }

                }

                else {

                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();

                }
            }
        }

        catch (IOException exception) { throw new RuntimeException(exception); }
    }

}

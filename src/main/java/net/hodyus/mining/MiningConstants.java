package net.hodyus.mining;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;

public interface MiningConstants {

    String MINING_WORLD = "mining";

    String COPY_OF_NAME = "mining-initial";
    File COPY_OF = Path.of(COPY_OF_NAME).toFile();

    Random STATIC_RANDOM = new Random();

}

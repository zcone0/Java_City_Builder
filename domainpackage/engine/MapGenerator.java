package domainpackage.engine;

import java.security.SecureRandom;

public class MapGenerator {

    public MapGenerator(){
        SecureRandom secureRandom = new SecureRandom();
        long randomSeed = secureRandom.nextLong();
        NoiseGenerator noise = new NoiseGenerator(randomSeed);
    }
}

import java.util.Scanner;
import java.util.Random;

public class tbRandomGenerator {
    private long seed;
    private double numbRandom;

    public tbRandomGenerator(long iptSeed) {
        this.seed = iptSeed;
        this.numbRandom = 0.0;
    }

    public double getRandom() {
        Random rand = new Random(seed);
        this.numbRandom = rand.nextDouble();
        return this.numbRandom;
    }

}
import java.util.Scanner;
import java.util.Random;

/**
 * Class ini adalah tbRandomGenerator, digunakan untuk mendapatkan nilai random
 * berdasarkan seed input dari user
 *
 * Class ini dibuat sendiri berdasarkan referensi dari
 * docs.oracle.com/javase/8/docs/api/java/util/Random.html
 *
 */
public class tbRandomGenerator {
    private long seed;      // seed dari user
    private double numbRandom;  // nilai double yang dirandom

    /**
     * Constructor untuk membuat random generator
     * @param iptSeed
     */
    public tbRandomGenerator(long iptSeed) {
        this.seed = iptSeed;
        this.numbRandom = 0.0;
    }

    /**
     * Getter untuk value random
     * @return sebuah angka random
     */
    public double getRandom() {
        Random rand = new Random(seed);
        this.numbRandom = rand.nextDouble();
        return this.numbRandom;
    }

}

/*
 * Vincent mark - 6182101013 ; 
 * William Arthur - 6182101012 ;
 * Marcellius - 6182101003
 * kelompok 12
 */
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
    private long seed; // seed dari user
    private double numbRandom; // nilai double yang dirandom
    private Random rand;

    /**
     * Constructor untuk membuat random generator
     * 
     * @param iptSeed
     */
    public tbRandomGenerator(long iptSeed) {
        this.seed = iptSeed;
        this.numbRandom = 0.0;
        this.rand = new Random();
        rand.setSeed(seed);
    }

    /**
     * Getter untuk value random
     * 
     * @return sebuah angka random
     */
    public double getRandom() {
        this.numbRandom = rand.nextDouble();
        return this.numbRandom;
    }

}
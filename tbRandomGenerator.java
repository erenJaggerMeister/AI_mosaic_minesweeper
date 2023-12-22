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

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // System.out.print("Enter a seed for the genetic algorithm: ");
    // long seed = scanner.nextLong();
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

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // System.out.print("Enter a seed for the genetic algorithm: ");
    // long seed = scanner.nextLong();

    // Random random = new Random(seed);

    // // Contoh: Generate 5 random numbers using the seed
    // System.out.println("Random numbers generated using the seed:");
    // for (int i = 0; i < 5; i++) {
    // System.out.println(random.nextDouble());
    // }
    // scanner.close();

    // System.out.println();
    // System.out.println("Math Rand");
    // System.out.println(Math.random());

    // /*
    // * 0.7308781907032909
    // * 0.41008081149220166
    // * 0.20771484130971707
    // * 0.3327170559595112
    // * 0.9677559094241207
    // *
    // * Math Rand
    // * 0.9555676326235688
    // */
    // }
}

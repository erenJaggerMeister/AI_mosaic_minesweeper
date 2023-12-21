import java.util.Random;
import java.util.Scanner;

public class tbAllOnesGA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Seed for Random :");
        long seed = sc.nextLong();

        System.out.println("Enter Matrix size :");
        int matrixSize = sc.nextInt();

        System.out.println("Enter Matrix :");
        int[][] matrixMosaic = new int[matrixSize][matrixSize];
        // inisialisasi
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixMosaic[i][j] = sc.nextInt();
                // input 0-9, -1 kalo kosong
                // Contoh input
                //  1 -1  1 -1  2
                // -1 -1 -1 -1 -1
                // -1  4 -1 -1  5
                // -1  6  8  7 -1
                // -1  4  6 -1  2
            }
        }

        tbGeneticAlgo ga = new tbGeneticAlgo(100, 0.05, 0.95, 0, matrixMosaic);
        tbPopulation population = ga.initPopulation(matrixSize * matrixSize);
        ga.evalPopulation(population);
        int generation = 1;
//        System.out.println(ga.isTerminationConditionMet(population));
        while (ga.isTerminationConditionMet(population) == false) {
//             System.out.println("Masuk dalam loop");
            // System.out.println(ga.isTerminationConditionMet(population));
            // Apply crossover
            population = ga.crossoverPopulation(population);
            // Apply mutation
            population = ga.mutatePopulation(population);
            // Evaluate population
            ga.evalPopulation(population);
            // Increment the current generation
            generation++;
        }

        // Print fittest individual from population
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + "\n" + population.getFittes(0).toString());
    }
}

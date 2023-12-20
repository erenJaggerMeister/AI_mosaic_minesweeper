import java.util.Random;
import java.util.Scanner;

public class tbAllOnesGA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrixMosaic = new int[5][5];
        // inisialisasi
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrixMosaic[i][j] = sc.nextInt();
                // input 0-9, -1 kalo kosong
                // 10102
                // 00000
                // 04005
                // 06870
                // 04602
            }
        }

        tbGeneticAlgo ga = new tbGeneticAlgo(100, 0.05, 0.95, 0, matrixMosaic);
        tbPopulation population = ga.initPopulation(25);
        ga.evalPopulation(population);
        int generation = 1;
        while (ga.isTerminationConditionMet(population) == true) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.getFittes(0).toString());
            // Apply crossover
            population = ga.crossoverPopulation(population);
            // Apply mutation
            population = ga.mutatePopulation(population);
            // Evaluate population
            ga.evalPopulation(population);
            // Increment the current generation
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittes(0).toString());
    }

}

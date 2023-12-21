import java.util.Random;
import java.util.Scanner;

public class tbAllOnesGA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //input matrix size
        int matrixSize = sc.nextInt();
        // inisialisasi
        int[][] matrixMosaic = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixMosaic[i][j] = sc.nextInt();
                // contoh: 
                // input 0-9, -1 kalo kosong
                // 10102
                // 00000
                // 04005
                // 06870
                // 04602
            }
        }

        tbGeneticAlgo ga = new tbGeneticAlgo(10, 0.05, 0.95, 0, matrixMosaic);
        tbPopulation population = ga.initPopulation(matrixSize * matrixSize);
        ga.evalPopulation(population);
        int generation = 1;
        System.out.println(ga.isTerminationConditionMet(population));
        while (ga.isTerminationConditionMet(population) == false) {
            // System.out.println("Masuk dalam loop");
            // System.out.println(ga.isTerminationConditionMet(population));
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

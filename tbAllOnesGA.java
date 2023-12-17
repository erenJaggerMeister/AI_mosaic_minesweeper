import java.util.Random;

public class tbAllOnesGA {
    public static void main(String[] args) {
        int[][] matrixMosaic = new int[5][5];
        // inisialisasi
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrixMosaic[i][j] = 0;
            }
        }
        matrixMosaic[0][0] = 1;
        matrixMosaic[0][1] = 0;
        matrixMosaic[0][2] = 1;
        matrixMosaic[0][3] = 0;
        matrixMosaic[0][4] = 2;

        matrixMosaic[1][0] = 0;
        matrixMosaic[1][1] = 0;
        matrixMosaic[1][2] = 0;
        matrixMosaic[1][3] = 0;
        matrixMosaic[1][4] = 0;

        matrixMosaic[2][0] = 0;
        matrixMosaic[2][1] = 4;
        matrixMosaic[2][2] = 0;
        matrixMosaic[2][3] = 0;
        matrixMosaic[2][4] = 5;

        matrixMosaic[3][0] = 0;
        matrixMosaic[3][1] = 6;
        matrixMosaic[3][2] = 8;
        matrixMosaic[3][3] = 7;
        matrixMosaic[3][4] = 0;

        matrixMosaic[4][0] = 0;
        matrixMosaic[4][1] = 4;
        matrixMosaic[4][2] = 6;
        matrixMosaic[4][3] = 0;
        matrixMosaic[4][4] = 2;
        tbGeneticAlgo ga = new tbGeneticAlgo(500, 0.05, 0.95, 0, matrixMosaic);
        tbPopulation population = ga.initPopulation(5);
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

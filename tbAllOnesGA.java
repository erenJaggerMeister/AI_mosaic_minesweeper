import java.util.Random;
import java.util.Scanner;
import java.util.*;
import javax.print.attribute.standard.PrinterName;
import java.io.*;

public class tbAllOnesGA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputNamaFile = "";
        System.out.print("Silahkan masukkan file input anda : ");
        inputNamaFile = sc.next();
        //
        try {
            File file = new File(inputNamaFile);
            Scanner scanner = new Scanner(file);
            // System.out.println("Enter Seed for Random :");
            // long seed = sc.nextLong();
            long seed = scanner.nextLong();

            // System.out.println("Enter Matrix size :");
            // int matrixSize = sc.nextInt();
            int matrixSize = scanner.nextInt();

            // System.out.println("Enter Matrix :");
            int[][] matrixMosaic = new int[matrixSize][matrixSize];
            // inisialisasi
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    // matrixMosaic[i][j] = sc.nextInt();
                    matrixMosaic[i][j] = scanner.nextInt();
                    // input 0-9, -1 kalo kosong
                    // Contoh input
                    // 1 -1 1 -1 2
                    // -1 -1 -1 -1 -1
                    // -1 4 -1 -1 5
                    // -1 6 8 7 -1
                    // -1 4 6 -1 2
                }
            }

            tbGeneticAlgo ga = new tbGeneticAlgo(100, 0.05, 0.95, 0, matrixMosaic, seed);
            tbPopulation population = ga.initPopulation(matrixSize * matrixSize);
            ga.evalPopulation(population);
            int generation = 1;
            // System.out.println(ga.isTerminationConditionMet(population));
            while (ga.isTerminationConditionMet(population) == false) {
                // System.out.println("Masuk dalam loop");
                // System.out.println(ga.isTerminationConditionMet(population));
                // Apply crossover
                population = ga.crossoverPopulation(population);
                // Apply mutation
                population = ga.mutatePopulation(population);
                // Evaluasi populasi
                ga.evalPopulation(population);
                // Increment generasinya

                // System.out.println("generation = " + generation + "\n" +
                // population.getFittes(0).toString());
                generation++;
                if (generation == 2147483647) {
                    break;
                }
            }

            // Print fittest individual from population
            String outputFileName = "outputHasil.txt";
            try (PrintWriter writer = new PrintWriter(outputFileName)) {
                writer.println("Found solution in " + generation + " generations");
                writer.println("Best solution: " + "\n" + population.getFittes(0).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}

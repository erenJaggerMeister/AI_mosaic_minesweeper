import java.util.Random;
import java.util.Scanner;
import java.util.*;
import javax.print.attribute.standard.PrinterName;
import java.io.*;

/**
 * Ini adalah Class Main yang akan di Run
 *
 * Input :
 * Berupa file yang terdiri dari :
 * 1.   input seed -> seed
 * 2.   input matrix size -> size
 * 3-n. input anggota matrix
 */
public class tbAllOnesGA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputNamaFile = "";
        // masukan berupa file txt dengan format penulisan sbb
        /*
         * seed
         * size
         * matrix size * size
         * 
         * contoh:
         * 8500
         * 5
         * 1 -1 1 -1 2
         * -1 -1 -1 -1 -1
         * -1 4 -1 -1 5
         * -1 6 8 7 -1
         * -1 4 6 -1 2
         */
        System.out.print("Silahkan masukkan file input anda : ");
        inputNamaFile = sc.next();
        //
        try {
            // input dari file.txt
            File file = new File(inputNamaFile);
            Scanner scanner = new Scanner(file);
            // System.out.println("Enter Seed for Random :");
            // long seed = sc.nextLong();
            long seed = scanner.nextLong();

            // System.out.println("Enter Matrix size :");
            // int matrixSize = sc.nextInt();
            int matrixSize = scanner.nextInt();     // input ukuran matrix

            // System.out.println("Enter Matrix :");
            int[][] matrixMosaic = new int[matrixSize][matrixSize]; // input ukuran matrix
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

            // inisialisasi genetic algorithm, (ditentukan sendiri) population size = 100,
            // mutation rate = 0.05, cross rate = 0.95, elit count = 0
            tbGeneticAlgo ga = new tbGeneticAlgo(100, 0.05, 0.95, 0, matrixMosaic, seed);
            // inisialisasi population dengan ukuran matrix size * size
            tbPopulation population = ga.initPopulation(matrixSize * matrixSize);

            // evaluasi population
            ga.evalPopulation(population);
            // varuable generation counter
            int generation = 1;
            // System.out.println(ga.isTerminationConditionMet(population));
            // selama terminate condition belum dipenuhi
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

            // Output fittest individual from population, nama filenya adalah
            // outputHasil.txt
            String outputFileName = "outputHasil.txt";
            try (PrintWriter writer = new PrintWriter(outputFileName)) {
                writer.println("Found solution in " + generation + " generations");
                writer.println("Best solution: " + "\n" + population.getFittes(0).toString());
            }
            System.out.print("Program telah selesai, cek outputHasil.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();        // output error
        }

    }

}
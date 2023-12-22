
/*
 * Vincent mark - 6182101013 ; 
 * William Arthur - 6182101012 ;
 * Marcellius - 6182101003
 * kelompok 12
 */
import java.util.*;

/**
 * Class ini digunakan untuk menyimpan banyak kromosom
 *
 * Sumber diambil dari buku Genetic Algorithms in Java Basics dan PPT Algoritma
 * genetik
 * 
 * @author Lee Jacobson & Burak Kanber
 */
public class tbPopulation {
    private tbIndividual population[];// pupulasi
    private double populationFitness = -1;// fitness dari populasi ini
    private long seed;

    /**
     * tbPopulaation adalah gabungan dari individual yang disimpan dalam sebuah
     * array
     * 
     * @param populationSize
     */
    public tbPopulation(int populationSize) {
        this.population = new tbIndividual[populationSize];
    }

    /**
     * tbPopulaation adalah gabungan dari individual yang disimpan dalam sebuah
     * array
     * 
     * @param populationSize
     * @param chromosomeLength
     * @param iptSeed
     */
    public tbPopulation(int populationSize, int chromosomeLength, long iptSeed) {
        this.population = new tbIndividual[populationSize];
        this.seed = iptSeed;
        // untuk setiap slot populasi, insialisasi individualnya terlebih dahulu
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            tbIndividual individual = new tbIndividual(chromosomeLength, this.seed);
            this.population[individualCount] = individual;
        }
    }

    // getter individual
    public tbIndividual[] getIndividuals() {
        return this.population;
    }

    /**
     * Fungsi untuk mendapatkan fittest individu dari sebuah pupulasi
     * 
     * @param offset
     * @return
     */
    public tbIndividual getFittes(int offset) {
        // override fungsi compare, agar mudah dilakukan sort
        // Sort array berdasarkan fitness yang ada pada masing-masing individu
        Arrays.sort(this.population, new Comparator<tbIndividual>() {
            @Override
            public int compare(tbIndividual o1, tbIndividual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        // System.out.println("INI PRINT DARI GETFITTES");
        // for (int i = 0; i < this.population.length; i++) {
        // System.out.println(i + " :");
        // System.out.println(getIndividual(i));
        // }
        return this.population[offset];
    }

    // setter population fitness
    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    // getter population fitness
    public double getPopulationFitness() {
        return this.populationFitness;
    }

    // getter poopulation size
    public int size() {
        return this.population.length;
    }

    // setter tbIndividual
    public tbIndividual setIndividual(int offset, tbIndividual individual) {
        return population[offset] = individual;
    }

    // getter tbIndividual
    public tbIndividual getIndividual(int offset) {
        return population[offset];
    }
}
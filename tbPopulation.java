import java.util.*;

/**
 * @author Lee Jacobson & Burak Kanber
 */
public class tbPopulation {
    private tbIndividual population[];// untuk menyimpan semua chromosome atau individu dalam satu tempat
    private double populationFitness = -1;// atribut untuk menyimpan nilai fitness, pada saat pecarian setelah
                                          // menghitung jumlah individu di dalam populasi, maka akan dihitung nilai
                                          // fitness yang terbaik diantara jumlah individu untuk dijadikan nilai fitness
                                          // populasi

    private long seed;// atribut untuk menyimpan nilai seed yang akan digunakan untuk random generator

    /**
     * @return method construct untuk inisialisasi
     * @param ukuran populasi
     */
    public tbPopulation(int populationSize) {
        this.population = new tbIndividual[populationSize];
    }

    public tbPopulation(int populationSize, int chromosomeLength, long iptSeed) {
        this.population = new tbIndividual[populationSize];
        this.seed = iptSeed;
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            tbIndividual individual = new tbIndividual(chromosomeLength, this.seed);
            this.population[individualCount] = individual;
        }
    }

    public tbIndividual[] getIndividuals() {
        return this.population;
    }

    public tbIndividual getFittes(int offset) {
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

    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    public double getPopulationFitness() {
        return this.populationFitness;
    }

    public int size() {
        return this.population.length;
    }

    public tbIndividual setIndividual(int offset, tbIndividual individual) {
        return population[offset] = individual;
    }

    public tbIndividual getIndividual(int offset) {
        return population[offset];
    }
}

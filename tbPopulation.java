import java.sql.SQLOutput;
import java.util.*;

public class tbPopulation {
    
    private tbIndividual population[];
    private double populationFitness = -1;
    /**
     * Create new object tbPopulation. tbPopulation is an array of individus
     * @param populationSize
     */
    public tbPopulation(int populationSize) {
        this.population = new tbIndividual[populationSize];
    }
    /**
     *  Create new object tbPopulation. tbPopulation is an array of individus
     * @param populationSize
     * @param chromosomeLength
     */
    public tbPopulation(int populationSize, int chromosomeLength) {
        this.population = new tbIndividual[populationSize];
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            tbIndividual individual = new tbIndividual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }
    /**
     * Getter of tbPopulation
     * @return
     */
    public tbIndividual[] getIndividuals() {
        return this.population;
    }

    /**
     * function to find the fittest individual in a population
     * @param offset
     * @return the fittest individual
     */
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
        System.out.println("INI PRINT DARI GETFITTES");
        for (int i = 0; i<this.population.length; i++){
            System.out.println(i + " :");
            System.out.println(getIndividual(i));
        }
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

    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            tbIndividual x = population[index];
            population[index] = population[i];
            population[i] = x;
        }
    }
}

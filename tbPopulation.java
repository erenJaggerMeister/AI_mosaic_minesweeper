import java.util.*;

public class tbPopulation {
    private tbIndividual population[];
    private double populationFitness = -1;

    public tbPopulation(int populationSize) {
        this.population = new tbIndividual[populationSize];
    }

    public tbPopulation(int populationSize, int chromosomeLength) {
        this.population = new tbIndividual[populationSize];
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            tbIndividual individual = new tbIndividual(chromosomeLength);
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

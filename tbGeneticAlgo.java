import java.util.Arrays;

public class tbGeneticAlgo {
    private int populationSize;
    private double mutationRate;
    private final double crossoverRate;
    private int elitismCount;
    private int[][] matrixSoal;

    public tbGeneticAlgo(int popSize, double mutRate, double crossRate, int elitCount, int[][] matricesQ) {
        this.populationSize = popSize;
        this.mutationRate = mutRate;
        this.crossoverRate = crossRate;
        this.elitismCount = elitCount;
        this.matrixSoal = matricesQ;
    }

    public tbPopulation initPopulation(int chromosomeLength) {
        tbPopulation population = new tbPopulation(this.populationSize, chromosomeLength);
        return population;
    }

    public double calcFitness(tbIndividual individual) {
        int correctGenes = 0;
        int numberGenes = countNumber();

        // everyNumberNeighbor[][0] -> menyimpan angka pada matrix
        // everyNumberNeighbor[][1] -> menyimpan jumlah neighbor
        // everyNumberNeighbor[][2] -> menyimpan jumlah overflow (neighbor yang
        // kelebihan)
        int[][] everyNumberNeighbor = new int[numberGenes][3];

        // Loop ke masing-masing angka dalam matrix, jika -1 maka skip
        int k = 0;
        for (int i = 0; i < this.matrixSoal.length; i++) {
            for (int j = 0; j < this.matrixSoal[0].length; j++) {
                if (this.matrixSoal[i][j] != -1) {
                    int[] temp = countNeighbor(individual, i, j);
                    everyNumberNeighbor[k][0] = this.matrixSoal[i][j];
                    everyNumberNeighbor[k][1] = temp[0];
                    everyNumberNeighbor[k][2] = temp[1];
                    k++;
                }
            }
        }

        // Hitung fitness
        double tempNeighbor = 0.0;
        double tempOverflow = 0.0;
        for (int i = 0; i < numberGenes; i++) {
            if (everyNumberNeighbor[i][0] != 0) {
                tempNeighbor += everyNumberNeighbor[i][1] * 1.0 / everyNumberNeighbor[i][0];
                tempOverflow += everyNumberNeighbor[i][2] * 1.0 / everyNumberNeighbor[i][0];
            } else {
                tempNeighbor += 1; // anggap 0/0 adalah 1, supaya tidak error
                tempOverflow += everyNumberNeighbor[i][2] * 2.0;
                // penalti dengan beban yang lebih berat dari 1, yaitu
                // dikali 2
            }
        }
        double fitness = (tempNeighbor - tempOverflow) / numberGenes; // variabel untuk store fitness

        // Store fitness
//        System.out.println(tempNeighbor + " " + tempOverflow + "\n" + "Fitness : " + fitness);
//        individual.setFitness(fitness);
        return fitness;
    }

    private int countNumber() {
        int counter = 0;
        for (int[] ints : this.matrixSoal) {
            for (int j = 0; j < this.matrixSoal[0].length; j++) {
                if (ints[j] == -1)
                    continue;
                else {
                    counter++;
                }
            }
        }
        return counter;
    }

    private int[] countNeighbor(tbIndividual individual, int i, int j) {
        // int[2] -> int[0] untuk menyimpan hasil dan int[1] untuk menyimpan overflow
        int[] arr = new int[2];
        int[][] tempMatrix = individual.getChromosomeMatrix(); // mengubah nxn kromosom menjadi matrix Nxn

        if (this.matrixSoal[i][j] != 0 && this.matrixSoal[i][j] != 9) {
            // Cek 1 baris di atasnya
            if (i != 0) {
                if (j != 0) arr[0] += tempMatrix[i - 1][j - 1];
                arr[0] += tempMatrix[i - 1][j];
                if (j != this.matrixSoal[0].length - 1) arr[0] += tempMatrix[i - 1][j + 1];
            }

            // Cek pada baris tersebut
            if (j != 0)
                arr[0] += tempMatrix[i][j - 1];
            arr[0] += tempMatrix[i][j];
            if (j != this.matrixSoal[0].length - 1)
                arr[0] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            if (i != this.matrixSoal.length - 1) {
                if (j != 0) {
                    arr[0] += tempMatrix[i + 1][j - 1];
                }
                arr[0] += tempMatrix[i + 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i + 1][j + 1];
            }

            if (arr[0] > this.matrixSoal[i][j]) {
                arr[1] = arr[0] - this.matrixSoal[i][j];
                arr[0] = this.matrixSoal[i][j];
            }
        }

        else if (this.matrixSoal[i][j] == 0) {
            // Cek 1 baris di atasnya
            arr[0] = 0;
            if (i != 0) {
                if (j != 0)
                    arr[1] += tempMatrix[i - 1][j - 1];
                arr[1] += tempMatrix[i - 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i - 1][j + 1];
            }

            // Cek pada baris tersebut
            if (j != 0)
                arr[1] += tempMatrix[i][j - 1];
            arr[1] += tempMatrix[i][j];
            if (j != this.matrixSoal[0].length - 1)
                arr[1] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            if (i != this.matrixSoal.length - 1) {
                if (j != 0) {
                    arr[1] += tempMatrix[i + 1][j - 1];
                }
                arr[1] += tempMatrix[i + 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i + 1][j + 1];
            }
        }

        else if (this.matrixSoal[i][j] == 9) {
            // Cek 1 baris di atasnya
            arr[1] = 0;
            if (i != 0) {
                if (j != 0)
                    arr[1] += tempMatrix[i - 1][j - 1];
                arr[1] += tempMatrix[i - 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i - 1][j + 1];
            }

            // Cek pada baris tersebut
            if (j != 0)
                arr[1] += tempMatrix[i][j - 1];
            arr[1] += tempMatrix[i][j];
            if (j != this.matrixSoal[0].length - 1)
                arr[1] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            if (i != this.matrixSoal.length - 1) {
                if (j != 0) {
                    arr[1] += tempMatrix[i + 1][j - 1];
                }
                arr[1] += tempMatrix[i + 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i + 1][j + 1];
            }
        }
//        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public void evalPopulation(tbPopulation population) {
        double populationFitness = 0;
        for (tbIndividual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(tbPopulation population) {
        for (tbIndividual x : population.getIndividuals()) {
            System.out.println(x.getFitness());
            System.out.println(Arrays.deepToString(x.getChromosomeMatrix()));
            if (x.getFitness() == 1) {
                return true;
            }
        }
        return false;
    }

    public tbIndividual selectParent(tbPopulation population) {
        // Get individuals
        tbIndividual individuals[] = population.getIndividuals();
        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;
        // Find parent
        double spinWheel = 0;
        for (tbIndividual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }

    public tbPopulation crossoverPopulation(tbPopulation population) {
        // Create new population
        tbPopulation newPopulation = new tbPopulation(population.size());
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            tbIndividual parent1 = population.getFittes(populationIndex);
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
                // Initialize offspring
                tbIndividual offspring = new tbIndividual(parent1.getChromosomeLength());
                // Find second parent
                tbIndividual parent2 = selectParent(population);
                // Loop over genome
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Use half of parent1's genes and half of parent2's genes
                    if (0.5 > Math.random()) {
                        // offspring.setGene(geneIndex,
                        // parent1.getGene(geneIndex));
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        // offspring.setGene(geneIndex,
                        // parent2.getGene(geneIndex));
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    public tbPopulation mutatePopulation(tbPopulation population) {
        // Initialize new population
        tbPopulation newPopulation = new tbPopulation(this.populationSize);
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            tbIndividual individual = population.getFittes(populationIndex);
            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                if (populationIndex >= this.elitismCount) {
                    if (this.mutationRate > Math.random()) {
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            // Add individual to population
            newPopulation.setIndividual(populationIndex, individual);
        }
        // Return mutated population
        return newPopulation;
    }
}

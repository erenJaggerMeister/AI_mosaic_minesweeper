public class tbGeneticAlgo {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    private int[][] matrixSoal;
    private int[][] copyMatrixSoal;

    public tbGeneticAlgo(int popSize, double mutRate, double crossRate, int elitCount, int[][] matricesQ) {
        this.populationSize = popSize;
        this.mutationRate = mutRate;
        this.crossoverRate = crossRate;
        this.elitismCount = elitCount;
        this.matrixSoal = matricesQ;
        this.copyMatrixSoal = matrixSoal;
    }

    public tbPopulation initPopulation(int chromosomeLength) {
        tbPopulation population = new tbPopulation(this.populationSize, chromosomeLength);
        return population;
    }

    public double calcFitness(tbIndividual individual) {
        // Track number of correct genes
        int correctGenes = 0;
        // Loop over individual's genes
        for (int i = 0; i < individual.getChromosomeLength(); i++) {
            for (int j = 0; j < individual.getChromosomeLength(); j++) {
                if (individual.getGene(i, j) == 1) { // 1 => hitam
                    // jika berada di baris paling atas
                    if (i == 0) {
                        if (j == 0) { // kolom awal
                            // cek angka di matrix soal selama bukan angka 0, maka angka di baris-kolom tsb
                            // bisa dikurangkan
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j + 1] != 0) {
                                copyMatrixSoal[i + 1][j + 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else if (j == 4) { // kolom akhir
                            // cek angka di matrix soal selama bukan angka 0, maka angka di baris-kolom tsb
                            // bisa dikurangkan
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j - 1] != 0) {
                                copyMatrixSoal[i + 1][j - 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else { // bukan kolom akhir maupun awal
                            // cek angka di matrix soal selama bukan angka 0, maka angka di baris-kolom tsb
                            // bisa dikurangkan
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j + 1] != 0) {
                                copyMatrixSoal[i + 1][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j - 1] != 0) {
                                copyMatrixSoal[i + 1][j - 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        }
                    } else if (i == 4) { // baris akhir
                        if (j == 0) { // kolom awal
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j + 1] != 0) {
                                copyMatrixSoal[i - 1][j + 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else if (j == 4) { // kolom akhir
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j - 1] != 0) {
                                copyMatrixSoal[i - 1][j - 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else {
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j + 1] != 0) {
                                copyMatrixSoal[i - 1][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j - 1] != 0) {
                                copyMatrixSoal[i - 1][j - 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        }
                    } else { // baris bukan di atas atau bawah
                        if (j == 0) { // kolom awal
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j + 1] != 0) {
                                copyMatrixSoal[i - 1][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j + 1] != 0) {
                                copyMatrixSoal[i + 1][j + 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else if (j == 4) { // kolom akhir
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j - 1] != 0) {
                                copyMatrixSoal[i - 1][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j - 1] != 0) {
                                copyMatrixSoal[i + 1][j - 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        } else {
                            int countPengurangan = 0;
                            if (copyMatrixSoal[i][j] != 0) {
                                copyMatrixSoal[i][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j - 1] != 0) {
                                copyMatrixSoal[i][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i][j + 1] != 0) {
                                copyMatrixSoal[i][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j] != 0) {
                                copyMatrixSoal[i - 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j - 1] != 0) {
                                copyMatrixSoal[i - 1][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i - 1][j + 1] != 0) {
                                copyMatrixSoal[i - 1][j + 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j] != 0) {
                                copyMatrixSoal[i + 1][j]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j - 1] != 0) {
                                copyMatrixSoal[i + 1][j - 1]--;
                                countPengurangan++;
                            }
                            if (copyMatrixSoal[i + 1][j + 1] != 0) {
                                copyMatrixSoal[i + 1][j + 1]--;
                                countPengurangan++;
                            }
                            // jika ternyata ada pengurangan di dalam copy matrix maka adalah correct genes
                            if (countPengurangan != 0) {
                                correctGenes++;
                            }
                        }
                    }
                }
            }
        }

        // Calculate fitness
        double fitness = (double) correctGenes;
        int countZero = 0;
        for (int i = 0; i < copyMatrixSoal.length; i++) {
            for (int j = 0; j < copyMatrixSoal[0].length; j++) {
                if (copyMatrixSoal[i][j] == 0) {
                    countZero++;
                }
            }
        }
        if (countZero < 3) {
            fitness = fitness * 100;
        } else if (countZero < 5) {
            fitness = fitness * 200;
        } else if (countZero < 8) {
            fitness = fitness * 300;
        } else if (countZero < 13) {
            fitness = fitness * 400;
        } else {
            fitness = fitness * 500;
        }
        // Store fitness
        individual.setFitness(fitness);
        return fitness;
    }

    public void evalPopulation(tbPopulation population) {
        double populationFitness = 0;
        for (tbIndividual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(tbPopulation population) {
        // for (tbIndividual individual : population.getIndividuals()) {
        // if (individual.getFitness() == 1) {
        // return true;
        // }
        // }
        // return false;
        boolean cekMatrisSoalKosong = true;
        for (int i = 0; i < copyMatrixSoal.length; i++) {
            for (int j = 0; j < copyMatrixSoal.length; j++) {
                if (copyMatrixSoal[i][j] != 0) {
                    cekMatrisSoalKosong = false;
                    break;
                }
            }
        }
        copyMatrixSoal = matrixSoal;
        return cekMatrisSoalKosong;
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
                        offspring.setGene(geneIndex, geneIndex, parent1.getGene(geneIndex, geneIndex));
                    } else {
                        // offspring.setGene(geneIndex,
                        // parent2.getGene(geneIndex));
                        offspring.setGene(geneIndex, geneIndex, parent2.getGene(geneIndex, geneIndex));
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
            for (int geneIndex_x = 0; geneIndex_x < individual.getChromosomeLength(); geneIndex_x++) {
                // // Skip mutation if this is an elite individual
                // if (populationIndex >= this.elitismCount) {
                // // Does this gene need mutation?
                // if (this.mutationRate > Math.random()) {
                // // Get new gene
                // int newGene = 1;

                // if (individual.getGene(geneIndex) == 1) {
                // newGene = 0;
                // }
                // // Mutate gene
                // individual.setGene(geneIndex, newGene);
                // }
                // }
                for (int geneIndex_y = 0; geneIndex_y < individual.getChromosomeLength(); geneIndex_y++) {
                    if (populationIndex >= this.elitismCount) {
                        if (this.mutationRate > Math.random()) {
                            int newGene = 1;
                            if (individual.getGene(geneIndex_x, geneIndex_y) == 1) {
                                newGene = 0;
                            }
                            individual.setGene(geneIndex_x, geneIndex_y, newGene);
                        }
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

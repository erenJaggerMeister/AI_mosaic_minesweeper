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
        int correctGenes = 0;
        int numberGenes = countNumber();

        // everyNumberNeighbor[][0] -> menyimpan angka pada matrix
        // everyNumberNeighbor[][1] -> menyimpan jumlah neighbor
        // everyNumberNeighbor[][2] -> menyimpan jumlah overflow (neighbor yang kelebihan)
        int[][] everyNumberNeighbor = new int[numberGenes][3];

        // Loop ke masing-masing angka dalam matrix, jika -1 maka skip
        int k = 0;
        for (int i = 0; i < this.matrixSoal.length; i++) {
            for (int j = 0; j < this.matrixSoal[0].length; j++) {
                int[] temp = countNeighbor(individual, i, j);
                everyNumberNeighbor[k][0] = this.matrixSoal[i][j];
                everyNumberNeighbor[k][1] = temp[0];
                everyNumberNeighbor[k][2] = temp[1];

                // Apabila neighbor value dan angka yang dicek sama, disertai jumlah overflow yang 0
                // maka perhitungan jumlah neighbor adalah benar (gen yang benar tambah 1)
                if(everyNumberNeighbor[k][0] == everyNumberNeighbor[k][1] &&
                   everyNumberNeighbor[k][2] == 0) correctGenes++;
                k++;
            }
        }

        // Hitung fitness
        double fitness = correctGenes*1.0 / numberGenes + 1;    // variabel untuk store hasil fitness
        double tempNeighbor = 0.0;
        double tempOverflow = 0.0;
        for(int i = 0; i<numberGenes; i++){
            if(everyNumberNeighbor[i][0] != 0) {
                tempNeighbor += everyNumberNeighbor[i][1] * 1.0 / everyNumberNeighbor[i][0];
                tempOverflow += everyNumberNeighbor[i][2] * 1.0 / everyNumberNeighbor[i][0];
            }
            else{
                tempNeighbor += 0;
                tempOverflow += everyNumberNeighbor[i][2] * 2.0;
            }
        }
        fitness += tempNeighbor - tempOverflow;

        // Store fitness
        individual.setFitness(fitness);
        return fitness;
    }

    private int countNumber(){
        int counter = 0;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(this.matrixSoal[i][j] == -1) continue;
                else{
                    counter++;
                }
            }
        }
        return counter;
    }

    private int[] countNeighbor(tbIndividual individual, int i, int j) {
        int[] arr = new int[2]; // int[2] -> int[0] untuk menyimpan hasil dan int[1] untuk menyimpan overflow

        int[][] tempMatrix = individual.getChromosomeMatrix();

        if(this.matrixSoal[i][j] != -1) {
            if(this.matrixSoal[i][j] != 0 && this.matrixSoal[i][j] != 9) {
                // Cek 1 baris di atasnya
                if (i != 0) {
                    if (j != 0) arr[0] += tempMatrix[i - 1][j - 1];
                    arr[0] += tempMatrix[i - 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[0] += tempMatrix[i - 1][j + 1];
                }

                // Cek pada baris tersebut
                if (j != 0) arr[0] += tempMatrix[i][j - 1];
                arr[0] += tempMatrix[i][j];
                if (j != this.matrixSoal[0].length - 1) arr[0] += tempMatrix[i][j + 1];

                // Cek 1 baris dibawahnya
                if (i != this.matrixSoal.length - 1) {
                    if (j != 0) {
                        arr[0] += tempMatrix[i + 1][j - 1];
                    }
                    arr[0] += tempMatrix[i + 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[0] += tempMatrix[i + 1][j + 1];
                }

                if(arr[0] > this.matrixSoal[i][j]){
                    arr[1] = arr[0]-this.matrixSoal[i][j];
                    arr[0] = this.matrixSoal[i][j];
                }
            }

            else if (this.matrixSoal[i][j] == 0) {
                // Cek 1 baris di atasnya
                arr[0] = 0;
                if (i != 0) {
                    if (j != 0) arr[1] += tempMatrix[i - 1][j - 1];
                    arr[1] += tempMatrix[i - 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i - 1][j + 1];
                }

                // Cek pada baris tersebut
                if (j != 0) arr[1] += tempMatrix[i][j - 1];
                arr[1] += tempMatrix[i][j];
                if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i][j + 1];

                // Cek 1 baris dibawahnya
                if (i != this.matrixSoal.length - 1) {
                    if (j != 0) {
                        arr[1] += tempMatrix[i + 1][j - 1];
                    }
                    arr[1] += tempMatrix[i + 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i + 1][j + 1];
                }
            }

            else if (this.matrixSoal[i][j] == 9) {
                // Cek 1 baris di atasnya
                arr[1] = 0;
                if (i != 0) {
                    if (j != 0) arr[1] += tempMatrix[i - 1][j - 1];
                    arr[1] += tempMatrix[i - 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i - 1][j + 1];
                }

                // Cek pada baris tersebut
                if (j != 0) arr[1] += tempMatrix[i][j - 1];
                arr[1] += tempMatrix[i][j];
                if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i][j + 1];

                // Cek 1 baris dibawahnya
                if (i != this.matrixSoal.length - 1) {
                    if (j != 0) {
                        arr[1] += tempMatrix[i + 1][j - 1];
                    }
                    arr[1] += tempMatrix[i + 1][j];
                    if (j != this.matrixSoal[0].length - 1) arr[1] += tempMatrix[i + 1][j + 1];
                }
            }
        }
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

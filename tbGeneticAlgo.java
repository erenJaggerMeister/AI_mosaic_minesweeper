import java.util.Arrays;

/**
 * @author Lee Jacobson & Burak Kanber, Genetic Algorithms in Java Basics
 */
public class tbGeneticAlgo {
    /*
     * populationSize untuk menyimpan jumlah individu di dalam satu populasi
     * mutationRate untuk menyimpan nilai ratio melakukan mutasi
     * crossoverRate untuk menyimpan seberapa besar dia melakukan crossover antara
     * satu individu dengan lainnya elitismCount untuk menyimpan
     * matrixSoal untuk mengisi mosaic soal yang akan dikerjakan nantinya
     * rand dengan tipe kelas tbRandomGenerator digunakan untuk merandom
     */
    private int populationSize;
    private double mutationRate;
    private final double crossoverRate;
    private int elitismCount;
    private int[][] matrixSoal;
    private tbRandomGenerator rand;
    private long seed;

    /**
     * 
     * @param popSize   seberapa banyak individu dalam 1 populasi
     * @param mutRate   seberapa besar akan melakukan mutasi
     * @param crossRate seberapa besar akan melakukan crossover antara satu individu
     *                  dengan yang lain
     * @param elitCount berapa banyak individu yang akan dipakai ke populasi
     *                  selanjutnya
     * @param matricesQ mosaic minesweeper yang diisikan dengan angka di baris dan
     *                  kolom tertentu
     * @param iptSeed   nilai seed
     */
    public tbGeneticAlgo(int popSize, double mutRate, double crossRate, int elitCount, int[][] matricesQ,
            long iptSeed) {
        this.populationSize = popSize;
        this.mutationRate = mutRate;
        this.crossoverRate = crossRate;
        this.elitismCount = elitCount;
        this.matrixSoal = matricesQ;
        this.rand = new tbRandomGenerator(iptSeed);
        this.seed = iptSeed;
    }

    /**
     * @param chromosomeLength berapa banyak individu/chromosome dalam satu populasi
     * @return meng-inisialisasi populasi
     */
    public tbPopulation initPopulation(int chromosomeLength) {
        tbPopulation population = new tbPopulation(this.populationSize, chromosomeLength, this.seed);
        return population;
    }

    /**
     * 
     * @param individual chromosome yang akan dihitung nilai fitnessnya
     * @return menghitung nilai fitness
     */
    public double calcFitness(tbIndividual individual) {
        int correctGenes = 0;
        int numberGenes = countNumber(); // menghitung berapa banyak angka di di mosaic

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
        // System.out.println(tempNeighbor + " " + tempOverflow + "\n" + "Fitness : " +
        // fitness);
        individual.setFitness(fitness);
        return fitness;
    }

    /**
     * 
     * @return menghitung berapa banyak angka di dalam mosaic
     */
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

    /**
     * 
     * @param individual mosaic
     * @param i          baris
     * @param j          kolom
     * @return menghitung berapa banyak "hitam" di tetangganya termasuk posisinya
     *         sendiri
     */
    private int[] countNeighbor(tbIndividual individual, int i, int j) {
        // int[2] -> int[0] untuk menyimpan hasil dan int[1] untuk menyimpan overflow
        int[] arr = new int[2];
        int[][] tempMatrix = individual.getChromosomeMatrix(); // mengubah nxn kromosom menjadi matrix Nxn

        if (this.matrixSoal[i][j] != 0 && this.matrixSoal[i][j] != 9) {
            // Cek 1 baris di atasnya
            if (i != 0) {
                if (j != 0)
                    arr[0] += tempMatrix[i - 1][j - 1];
                arr[0] += tempMatrix[i - 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i - 1][j + 1];
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
                    arr[0] += tempMatrix[i - 1][j - 1];
                arr[0] += tempMatrix[i - 1][j];
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i - 1][j + 1];
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
        }
        // System.out.println(Arrays.toString(arr));
        return arr;
    }

    /**
     * 
     * @param population populasi
     * @return menghitung nilai fitness dari populasi dengan cara menambahkan nilai
     *         fitness setiap chromosome atau individu di populasi tersebut
     */
    public void evalPopulation(tbPopulation population) {
        double populationFitness = 0;
        for (tbIndividual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }

    /**
     * 
     * @param population populasi
     * @return mengecek apakah kondisi berhenti dari sebuah looping untuk mencari
     *         solusi sudah ditemukan atau belum
     */
    public boolean isTerminationConditionMet(tbPopulation population) {
        for (tbIndividual x : population.getIndividuals()) {
            // System.out.println(x.getFitness());
            // System.out.println(Arrays.deepToString(x.getChromosomeMatrix()));
            if (x.getFitness() == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param population
     * @return memilih parent yang nanti akan dipakai untuk anaknya
     */
    public tbIndividual selectParent(tbPopulation population) {
        // Get individuals
        tbIndividual individuals[] = population.getIndividuals();
        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        // double rouletteWheelPosition = Math.random() * populationFitness;
        // TODO!!!
        // atas adalah kode asli dari sebelum pembuatan kelas random generator karena
        // menggunakan math.random()
        // bawah adalah penggunaan kelas random generator
        double rouletteWheelPosition = rand.getRandom() * populationFitness;
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

    /**
     * 
     * @param population
     * @return membuat individu baru dari 2 parent
     */
    public tbPopulation crossoverPopulation(tbPopulation population) {
        // Create new population
        tbPopulation newPopulation = new tbPopulation(population.size());
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            tbIndividual parent1 = population.getFittes(populationIndex);
            // Apply crossover to this individual?
            if (this.crossoverRate > rand.getRandom() && populationIndex > this.elitismCount) {
                // Initialize offspring
                tbIndividual offspring = new tbIndividual(parent1.getChromosomeLength(), this.seed);
                // Find second parent
                tbIndividual parent2 = selectParent(population);
                // Loop over genome
                // TODO!!!
                // silahkan matikan komentar jika ingin menjalankan
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

                // TODO!!!
                // pembagian chromosome split 2
                // misal 111000111 -> 111|000|111 chromosome 1
                // misal 000111000 -> 000|111|000 chromosome 2
                // maka menjadi 111|111|1111
                // for (int geneIndex = offspring.getChromosomeLength() / 3; geneIndex <
                // (offspring.getChromosomeLength()
                // - (offspring.getChromosomeLength() / 3)); geneIndex++) {
                // if (0.5 > Math.random()) {
                // // offspring.setGene(geneIndex,
                // // parent1.getGene(geneIndex));
                // offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                // } else {
                // // offspring.setGene(geneIndex,
                // // parent2.getGene(geneIndex));
                // offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                // }
                // }
                //
                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    /**
     * 
     * @param population
     * @return mutasi gene pada individu atau chromosome
     */
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
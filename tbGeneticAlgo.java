
/*
 * Vincent mark - 6182101013 ; 
 * William Arthur - 6182101012 ;
 * Marcellius - 6182101003
 * kelompok 12
 */
import java.util.Arrays;

/**
 * Sumber diambil dari buku Genetic Algorithms in Java Basics dan PPT Algoritma
 * genetik
 *
 * @author Lee Jacobson & Burak Kanber
 * @see tbIndividual untuk store kromosom
 * @see tbRandomGenerator untuk membuat random berdasarkan input seed
 *
 *      Fitness :
 *      - Membuat sendiri
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
     * Method untuk menghitung nilai fitness
     * 
     * @param individual chromosome yang akan dihitung nilai fitnessnya
     * @return sebuah nilai yang menentukan sebuah kromosom jelek/bagus
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
     * Method untuk menghitung berapa banyak angka dalam mosaic
     * 
     * @return banyak angka di dalam mosaic
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
     * Method untuk menghitung banyaknya "hitam" di 9 posisi disekitarnya
     * 
     * @param individual mosaic
     * @param i          baris
     * @param j          kolom
     * @return int[] = kromosom
     */
    private int[] countNeighbor(tbIndividual individual, int i, int j) {
        // int[2] -> int[0] untuk menyimpan hasil dan int[1] untuk menyimpan overflow
        int[] arr = new int[2];
        int[][] tempMatrix = individual.getChromosomeMatrix(); // mengubah nxn kromosom menjadi matrix Nxn

        // Jika nomor yang dicek bukan 0 dan bukan 9, maka jalankan logic dibawah ini
        if (this.matrixSoal[i][j] != 0 && this.matrixSoal[i][j] != 9) {
            // Cek 1 baris di atasnya
            // Out of bounds handler (atas)
            if (i != 0) {
                // Out of bounds handler (kiri)
                if (j != 0)
                    arr[0] += tempMatrix[i - 1][j - 1];
                arr[0] += tempMatrix[i - 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i - 1][j + 1];
            }

            // Cek neighbor pada baris tersebut
            // Out of bounds handler (kiri)
            if (j != 0)
                arr[0] += tempMatrix[i][j - 1];
            arr[0] += tempMatrix[i][j];
            // Out of bounds handler (kanan)
            if (j != this.matrixSoal[0].length - 1)
                arr[0] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            // Out of bounds handler (bawah)
            if (i != this.matrixSoal.length - 1) {
                // Out of bounds handler (kiri)
                if (j != 0) {
                    arr[0] += tempMatrix[i + 1][j - 1];
                }
                arr[0] += tempMatrix[i + 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i + 1][j + 1];
            }

            // If dibawah ini mengecek apabila nilai hasil lebih besar daripada angka yang
            // dicek,
            // maka masukan selisih (overflow) ke arr[1] -> mengindikasikan jumlah yang
            // kelebihan
            if (arr[0] > this.matrixSoal[i][j]) {
                arr[1] = arr[0] - this.matrixSoal[i][j]; // masukan selisih
                arr[0] = this.matrixSoal[i][j]; // masukan menjadi angka matrix
            }
        }

        // Jika angka yang dicek adalah 0, maka ubah neighbor yang dicek menjadi 0
        // Karena 0 tidak akan diisi oleh angka apapun
        // Jika sekitarnya diisi oleh kotak hitam, maka tambah arr[1] = overflow
        else if (this.matrixSoal[i][j] == 0) {
            // Cek 1 baris di atasnya
            // angka 0 seharusnya tidak memiliki angka apapun disekitarnya
            arr[0] = 0;
            // Out of bounds handler (atas)
            if (i != 0) {
                // Out of bounds handler (kiri)
                if (j != 0)
                    arr[1] += tempMatrix[i - 1][j - 1];
                arr[1] += tempMatrix[i - 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i - 1][j + 1];
            }

            // Cek pada baris tersebut
            // Out of bounds handler (kiri)
            if (j != 0)
                arr[1] += tempMatrix[i][j - 1];
            arr[1] += tempMatrix[i][j];
            // Out of bounds handler (kanan)
            if (j != this.matrixSoal[0].length - 1)
                arr[1] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            // Out of bounds handler (bawah)
            if (i != this.matrixSoal.length - 1) {
                // Out of bounds handler (kiri)
                if (j != 0) {
                    arr[1] += tempMatrix[i + 1][j - 1];
                }
                arr[1] += tempMatrix[i + 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[1] += tempMatrix[i + 1][j + 1];
            }
        }

        // Jika angka yang dicek pada matrix adalah 9,
        // maka tidak akan pernah kelebihan karena 9 angka sekitarnya diisi oleh angka 1
        // Akan kekurangan ataupun pas nilainya
        else if (this.matrixSoal[i][j] == 9) {
            // Cek 1 baris di atasnya
            arr[1] = 0; // set arr[1] menjadi 0 karena tidak akan pernah kelebihan
            // Out of bounds handler (atas)
            if (i != 0) {
                // Out of bounds handler (kiri)
                if (j != 0)
                    arr[0] += tempMatrix[i - 1][j - 1];
                arr[0] += tempMatrix[i - 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i - 1][j + 1];
            }

            // Cek pada baris tersebut
            // Out of bounds handler (kiri)
            if (j != 0)
                arr[0] += tempMatrix[i][j - 1];
            arr[0] += tempMatrix[i][j];
            // Out of bounds handler (kanan)
            if (j != this.matrixSoal[0].length - 1)
                arr[0] += tempMatrix[i][j + 1];

            // Cek 1 baris dibawahnya
            // Out of bounds handler (bawah)
            if (i != this.matrixSoal.length - 1) {
                // Out of bounds handler (kiri)
                if (j != 0) {
                    arr[0] += tempMatrix[i + 1][j - 1];
                }
                arr[0] += tempMatrix[i + 1][j];
                // Out of bounds handler (kanan)
                if (j != this.matrixSoal[0].length - 1)
                    arr[0] += tempMatrix[i + 1][j + 1];
            }
        }
        // System.out.println(Arrays.toString(arr));
        return arr;
    }

    /**
     * Menghitung nilai fitness dari populasi dengan cara menambahkan nilai
     * fitness setiap chromosome atau individu di populasi tersebut
     * 
     * @param population populasi
     *
     */
    public void evalPopulation(tbPopulation population) {
        double populationFitness = 0;
        for (tbIndividual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual); // menghitung fitnes dari sebuah populasi dengan menghitung
                                                          // dari masing-masing individu
        }
        population.setPopulationFitness(populationFitness);
    }

    /**
     * Mengecek apakah kondisi berhenti dari sebuah looping untuk mencari solusi
     * sudah ditemukan atau belum
     * 
     * @param population populasi
     * @return true jika telah menemukan fitness 1.0, false jika kurang dari 1.0
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
     * Memilih parent yang nanti akan dipakai untuk anaknya
     * 
     * @param population
     * @return tbIndividual; sebuah individu hasil pemilihan parent
     */
    public tbIndividual selectParent(tbPopulation population) {
        // Untuk mendapatkan sebuah individu
        tbIndividual individuals[] = population.getIndividuals();
        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        // double rouletteWheelPosition = Math.random() * populationFitness;
        // TODO!!!
        // atas adalah kode asli dari sebelum pembuatan kelas random generator karena
        // menggunakan math.random()
        // bawah adalah penggunaan kelas random generator
        double rouletteWheelPosition = rand.getRandom() * populationFitness; // Mendapatkan posisi roulette dengan
                                                                             // populasi yang ada
        // double rouletteWheelPosition = Math.random() * populationFitness;
        // Find parent
        double spinWheel = 0;
        // Loop dilakukan untuk mengiterasi masing-masing individu
        for (tbIndividual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }

    /**
     * Membuat individu baru dari 2 parent
     * 
     * @param population
     * @return tbPopulation; Populasi setelah dilakukan pemilihan parent
     */
    public tbPopulation crossoverPopulation(tbPopulation population) {
        // Buat population yang baru
        tbPopulation newPopulation = new tbPopulation(population.size());
        // Loop population saat ini berdasarkan fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            tbIndividual parent1 = population.getFittes(populationIndex);
            // Apply crossover terhadap individual
            if (this.crossoverRate > rand.getRandom() && populationIndex > this.elitismCount) {
                // if (this.crossoverRate > Math.random() && populationIndex >
                // this.elitismCount) {
                // Initialize offspring
                tbIndividual offspring = new tbIndividual(parent1.getChromosomeLength(), this.seed);
                // Cari parent kedua
                tbIndividual parent2 = selectParent(population);
                // Loop gen yang ada
                // TODO!!!
                // silahkan matikan komentar jika ingin menjalankan
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Gunakan gen dari setengah parent1 dan setengah parent2
                    if (0.5 > rand.getRandom()) {
                        // if (0.5 > Math.random()) {
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
     * Method untuk melakukan mutasi gene pada individu atau chromosome
     * 
     * @param population populasi yang ingin dilakukan mutasi
     * @return tbPopulation; Populasi yang telah dimutasi
     */
    public tbPopulation mutatePopulation(tbPopulation population) {
        // Inisialisasi populasi yang baru
        tbPopulation newPopulation = new tbPopulation(this.populationSize);
        // Loop populasi saat ini berdasarkan fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            tbIndividual individual = population.getFittes(populationIndex);
            // Loop berdasarkan masing-masing gen pada individu
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                if (populationIndex >= this.elitismCount) {
                    // if (this.mutationRate > Math.random()) {
                    if (this.mutationRate > rand.getRandom()) {
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
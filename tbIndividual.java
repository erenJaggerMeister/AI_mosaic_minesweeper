public class tbIndividual {
    private final int[] chromosome;     // array of int untuk store gen
    private double fitness = -1;        // tipe data double untuk store fitness value
    private long seed;

    /**
     * Digunakan untuk inisialisasi kromosom menggunakan array
     * @param chromosome
     */
    public tbIndividual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * Digunakan untuk inisialisasi kromosom menggunakan ukuran matrix NxN
     * @param chromosomeLength = ukuran matrix yang ada pada input class Main
     * @param iptSeed seed yang akan digunakan dalam random generator
     */
    public tbIndividual(int chromosomeLength, long iptSeed) {
        this.chromosome = new int[chromosomeLength];
        this.seed = iptSeed;
        tbRandomGenerator rand = new tbRandomGenerator(iptSeed);
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < rand.getRandom()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    /**
     * Getter untuk kromosom
     * @return chromosome = kromosom yang ingin diakses; Tipe data int[]
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * Getter untuk kromosom (dalam bentuk matrix)
     * @return chromosome = kromosom yang ingin diakses; Tipe data int[][]
     */
    public int[][] getChromosomeMatrix() {
        int sqrt = (int) Math.sqrt(this.chromosome.length);     // Menggunakan akar karena ukuran kromosom adalah NxN
        int[][] tempMatrix = new int[sqrt][sqrt];
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                int indexChromosome = j + (i * sqrt);
                tempMatrix[i][j] = this.chromosome[indexChromosome];
            }
        }
        return tempMatrix;
    }

    /**
     * Getter untuk ukuran kromosom
     * @return ukuran dari kromosom; tipe data int
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * Setter dari sebuah gen/alel
     * @param offset_1 index yang akan dituju; tipe data int
     * @param gene value yang akan mengganti value lama
     */
    public void setGene(int offset_1, int gene) {
        this.chromosome[offset_1] = gene;
    }

    /**
     * Getter dari sebuah gen/alel
     * @param offset_1 index yang akan dituju; tipe data int
     * @return
     */
    public int getGene(int offset_1) {
        return this.chromosome[offset_1];
    }

    /**
     * Setter untuk memasang fitness dalam object kromosom
     * @param fitness value seberapa oke/bagus sebuah individu dalam suatu case yang akan disolve
     *                range fitness ada pada -9 sampai 1
     *                -9 = jelek sekali
     *                1  = sangat bagus (jawabannya)
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    @Override
    public String toString() {
        int sqrt = (int) Math.sqrt(this.chromosome.length);
        StringBuilder output = new StringBuilder();
        for (int gene_1 = 0; gene_1 < sqrt; gene_1++) {
            for (int gene_2 = 0; gene_2 < sqrt; gene_2++) {
                output.append(this.chromosome[gene_1 * sqrt + gene_2]).append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }
}

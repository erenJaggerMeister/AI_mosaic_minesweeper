public class tbIndividual {
    private final int[] chromosome;
    private double fitness = -1;
    private long seed;

    /**
     * tbIndividual adalah gabungan gene, membentuk sebuah chromosome
     * 
     * @param chromosome
     */
    public tbIndividual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * tbIndividual adalah gabungan gene, membentuk sebuah chromosome
     * 
     * @param chromosomeLength
     * @param iptSeed
     */
    public tbIndividual(int chromosomeLength, long iptSeed) {
        // konstruktor ini akan membuat chromosome terlebih dahulu dengan cara random
        this.chromosome = new int[chromosomeLength];
        this.seed = iptSeed;
        tbRandomGenerator rand = new tbRandomGenerator(iptSeed);

        // untul setiap gene dari chromosome
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < rand.getRandom()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    /**
     * Getter chromosome dalam bentuk array
     * 
     * @return chromosome
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * getter chromosome dalam bentuk matrix
     * 
     * @return matrix
     */
    public int[][] getChromosomeMatrix() {
        int sqrt = (int) Math.sqrt(this.chromosome.length);
        int[][] tempMatrix = new int[sqrt][sqrt];
        // untuk setiap index dari matrix
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                int indexChromosome = j + (i * sqrt);
                tempMatrix[i][j] = this.chromosome[indexChromosome];
            }
        }
        return tempMatrix;
    }

    // getter chromosome length
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    // setter gene
    public void setGene(int offset_1, int gene) {
        this.chromosome[offset_1] = gene;
    }

    // getter gene
    public int getGene(int offset_1) {
        return this.chromosome[offset_1];
    }

    // setter fitness
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // getter fitness
    public double getFitness() {
        return this.fitness;
    }

    // print chromosome dalam bnetuk matrix
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

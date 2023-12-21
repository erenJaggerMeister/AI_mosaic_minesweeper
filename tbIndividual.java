public class tbIndividual {
    private final int[] chromosome;
    private double fitness = -1;

    // TODO!!!
    private long seed;

    public tbIndividual(int[] chromosome) {
        this.chromosome = chromosome;
    }

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

    public int[] getChromosome() {
        return this.chromosome;
    }

    public int[][] getChromosomeMatrix() {
        int sqrt = (int) Math.sqrt(this.chromosome.length);
        int[][] tempMatrix = new int[sqrt][sqrt];
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                int indexChromosome = j + (i * sqrt);
                tempMatrix[i][j] = this.chromosome[indexChromosome];
            }
        }
        return tempMatrix;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset_1, int gene) {
        this.chromosome[offset_1] = gene;
    }

    public int getGene(int offset_1) {
        return this.chromosome[offset_1];
    }

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

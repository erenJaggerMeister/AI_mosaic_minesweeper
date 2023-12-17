public class tbIndividual {
    private int[][] chromosome;
    private double fitness = -1;

    public tbIndividual(int[][] chromosome) {
        this.chromosome = chromosome;
    }

    public tbIndividual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength][chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            for (int gene_2 = 0; gene_2 < chromosomeLength; gene_2++) {
                if (0.5 < Math.random()) {
                    this.setGene(gene, gene_2, 1);
                } else {
                    this.setGene(gene, gene_2, 0);
                }
            }
        }
    }

    public int[][] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset_1, int offset_2, int gene) {
        this.chromosome[offset_1][offset_2] = gene;
    }

    public int getGene(int offset_1, int offset_2) {
        return this.chromosome[offset_1][offset_2];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        String output = "";
        for (int gene_1 = 0; gene_1 < this.chromosome.length; gene_1++) {
            for (int gene_2 = 0; gene_2 < this.chromosome[0].length; gene_2++) {
                output += this.chromosome[gene_1][gene_2];
            }
            output += "\n";
        }
        return output;
    }
}

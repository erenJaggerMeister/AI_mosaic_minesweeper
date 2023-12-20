public class tbIndividual {
    private int[] chromosome;
    private double fitness = -1;

    public tbIndividual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public tbIndividual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
                if (0.5 < Math.random()) {
                    this.setGene(gene, 1);
                } else {
                    this.setGene(gene, 0);
            }
        }
    }

    public int[] getChromosome() {
        return this.chromosome;
    }
    public int[][] getChromosomeMatrix(){
        int[][] tempMatrix = new int[5][5];
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                tempMatrix[i][j] = this.chromosome[j+i*5];
            }
        }
        return tempMatrix;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset_1, int gene) {
        this.chromosome[offset_1]= gene;
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
        String output = "";
        for (int gene_1 = 0; gene_1 < this.chromosome.length; gene_1++) {
            output += this.chromosome[gene_1];
            output += "\n";
        }
        return output;
    }
}

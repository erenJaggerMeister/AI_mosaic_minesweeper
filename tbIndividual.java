/*
 * Vincent mark - 6182101013 ; 
 * William Arthur - 6182101012 ;
 * Marcellius - 6182101003
 * kelompok 12
 */
/**
 * Class tbIndividual adalah class yang digunakan untuk menyimpan suatu
 * individu/ kromosom
 *
 * Terdiri dari :
 * - kromosom -> int[] dengan ukuran matrix size * matrix size
 * - fitness -> sebagai nilai evaluasi sebuah kromosom lebih baik/jelek
 * dibandingkan kromosom lainnya
 * - seed -> sebuah angka yang digunakan untuk random generator
 *
 * Referensi :
 * Sumber diambil dari buku Genetic Algorithms in Java Basics dan PPT Algoritma
 * genetik
 * 
 * @author Lee Jacobson & Burak Kanber
 */
public class tbIndividual {
    private final int[] chromosome;
    private double fitness = -1;
    private long seed;

    /**
     * tbIndividual adalah gabungan gene, membentuk sebuah chromosome
     * 
     * @param chromosome sebuah matrix kromosom
     */
    public tbIndividual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * tbIndividual adalah gabungan gene, membentuk sebuah chromosome
     * 
     * @param chromosomeLength ukuran suatu kromosom
     * @param iptSeed          seed
     */
    public tbIndividual(int chromosomeLength, long iptSeed) {
        // konstruktor ini akan membuat chromosome terlebih dahulu dengan cara random
        this.chromosome = new int[chromosomeLength];
        this.seed = iptSeed;
        tbRandomGenerator rand = new tbRandomGenerator(this.seed);

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

    /**
     * Getter chromosome length
     * 
     * @return sebuah ukuran dari kromosom
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * Setter untuk gen
     * 
     * @param offset_1 sebuah index untuk mengakses kromosom tsb
     * @param gene     gen yang akan menggantikan nilai gen lama
     */
    public void setGene(int offset_1, int gene) {
        this.chromosome[offset_1] = gene;
    }

    /**
     * Getter untuk gen
     * 
     * @param offset_1 untuk mengakses kira-kira index gen mana yang akan diprint
     * @return sebuah gen berdasarkan offset
     */
    public int getGene(int offset_1) {
        return this.chromosome[offset_1];
    }

    /**
     * Setter fitness
     * 
     * @param fitness sebuah value yang mendeterminasi sebuah solusi lebih
     *                baik/jelek dari kromosom lain
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * Getter untuk fitness
     * 
     * @return nilai fitness dari sebuah kromosom
     */
    public double getFitness() {
        return this.fitness;
    }

    /**
     * Override method toString untuk memprint sebuah kromosom menjadi matrix
     * 
     * @return String
     */
    @Override
    public String toString() {
        int sqrt = (int) Math.sqrt(this.chromosome.length); // gunakan akar karena ukuran matrix adalah size matrix *
                                                            // size matrix
        StringBuilder output = new StringBuilder(); // string builder untuk menyimpan output
        for (int gene_1 = 0; gene_1 < sqrt; gene_1++) {
            for (int gene_2 = 0; gene_2 < sqrt; gene_2++) {
                output.append(this.chromosome[gene_1 * sqrt + gene_2]).append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
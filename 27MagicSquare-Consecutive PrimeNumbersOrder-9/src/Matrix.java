import java.util.ArrayList;
import java.util.Random;

public class Matrix {

    public int[][] matrix;
    private int size;

    public Matrix (int n){
        matrix = new int[n][n];
        this.size = n;
    }

    public void createConsecutivePrimeMatrix (int max) {
        // Create ArrayList with prime numbers until max
        ArrayList<Integer> prime = PrimeGenerator.createPrimeList(max);

        // Random-Start-Index
        Random r = new Random();
        int start = r.nextInt(prime.size()-(size*size));

        // Randomly fill in the primes start with the Random-Start-Index
        int height = 0;
        int width = 0;
        for(int j = start; j < (start+(size*size)); j++) {
            do {
                height = r.nextInt(size - 1);
                width = r.nextInt(size - 1);
            } while (matrix[width][height] != 0);
            matrix[width][height] = j;
        }
    }

}

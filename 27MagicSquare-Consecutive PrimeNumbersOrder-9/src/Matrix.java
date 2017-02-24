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
        ArrayList<Integer> prime = PrimeGenerator.createPrimeList(max);

        // Random-Start-Index
        Random r = new Random();
        r.nextInt(prime.size()-(size*size));


    }

}

import java.util.ArrayList;

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
        MersenneTwisterFast r = new MersenneTwisterFast();
        int start = r.nextInt(prime.size()-(size*size)+1);

        // Randomly fill in the primes start with the Random-Start-Index
        int height = 0;
        int width = 0;
        for(int j = start; j < (start+(size*size)); j++) {
            do {
                height = r.nextInt(size);
                width = r.nextInt(size);
            } while (matrix[width][height] != 0);
            matrix[width][height] = j;
        }
    }

    public static Matrix generate(int n, int max) {
        Matrix matrix = new Matrix(n);
        matrix.createConsecutivePrimeMatrix(max);
        return  matrix;
    }

    public boolean isMagic(){
        // set sum of first line
        int sum = arraySum(matrix[0]);

        // check sum of lines
        for(int j = 1; j < size; j++){
            // TODO mit Lambda ? int sum = new ArrayList<Integer>(line).stream().collect(Collectors.summarizingInt())
            // new ArrayList<Integer>(arr).stream().collect(Collectors.summarizingInt()).getSum();
            int sumL = arraySum(matrix[j]);
            if (sum != sumL) {
                return false;
            }
        }

        // check sum of rows
        for(int k = 0; k < size; k++){
            int sumR = arraySum(getRow(k));
            if (sum != sumR) {
                return false;
            }
        }

        return true;
    };

    private int arraySum(int[] input){
        int sum = 0;
        for (int i:input) {
            sum += i;
        }
        return sum;
    }

    public void setRow(int r, int[] input){
        for (int j = 0; j < size; j++) {
            matrix[j][r] = input[j];
        }
    }

    public int[] getRow(int r){
        int[] row = new int[size];
        for (int j = 0; j < size; j++) {
            row[j]=matrix[j][r];
        }
        return row;
    }

    public int getSize() {
        return size;
    }
    @Override
    public String toString() {
        String output = "";
        for(int line = 0; line < size; line++){
            for(int col = 0; col < size; col++){
                if((matrix[line][col] + "").length()<3){
                    if((matrix[line][col] + "").length()<2){
                        output += " ";
                    }
                    output += " ";
                }
                output += (matrix[line][col] + " ");
            }
            output += "\n";
        }
        return output;
    }
}
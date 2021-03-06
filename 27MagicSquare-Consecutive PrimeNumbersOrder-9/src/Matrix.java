import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private int[][] matrix;
    private int size;
    private MersenneTwisterFast random = new MersenneTwisterFast();

    private Matrix (){
        this.size = Configuration.instance.matrixSize;
        matrix = new int[size][size];
    }

    public Matrix(int[][] m){
        matrix = m;
        this.size = m.length;
    }

    public void createConsecutivePrimeMatrix () {
        // Create ArrayList with prime numbers until max
        ArrayList<Integer> prime = PrimeGenerator.createPrimeList();

        // Random-Start-Index
        int start = random.nextInt(prime.size()-(size*size)+1);
        List<Integer> input = prime.subList(start, start + (size*size));

        int t = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j] = input.get(t);
                t++;
            }
        }

        new ShakeMatrix().doStrategy(this);
    }

    public static Matrix generate() {
        Matrix matrix = new Matrix();
        matrix.createConsecutivePrimeMatrix();
        return  matrix;
    }

    public boolean isMagic(){
        // set sum of first line
        int sum = arraySum(matrix[0]);

        // check sum of lines
        for(int j = 1; j < size; j++) {
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

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        String output = "";
        for (int line = 0; line < size; line++) {
            for (int col = 0; col < size; col++) {
                if ((matrix[line][col] + "").length() < 3) {
                    if ((matrix[line][col] + "").length() < 2) {
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

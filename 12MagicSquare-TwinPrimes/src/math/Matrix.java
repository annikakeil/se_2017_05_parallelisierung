package math;


import sun.applet.Main;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Matrix {

    int matrix[][] = new int[4][4]; // TODO Configuration

    public Matrix() {}

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isValid() {
        int value = sumColumn(0);
        for (int i = 0; i < 4; i++) {
            if (value != sumColumn(i) || value != sumRow(i)) {
                return false;
            }
        }
        return true;
    }

    private int sumColumn(int index) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[index][i];
        }
        return sum;
    }

    private int sumRow(int index) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][index];
        }
        return sum;
    }

    public int getValue() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += sumRow(i);
        }
        return sum;
    }

    public void fillEmpty(int value) {
        Random random = new Random();
        while (true) {
            int width = random.nextInt(matrix.length);
            int height = random.nextInt(matrix.length);
            if (matrix[width][height] == 0) {
                matrix[width][height] = value;
                break;
            }
        }
    }

    public static Matrix generate() {
        List<TwinPair> pairs = TwinPair.getPairs(1000);
        Collections.shuffle(pairs);
        Matrix matrix = new Matrix();
        for (TwinPair pair : pairs.stream().limit(8).collect(Collectors.toList())) {
            matrix.fillEmpty(pair.getFirst());
            matrix.fillEmpty(pair.getSecond());
        }
        return matrix;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int height = 0; height < matrix.length; height++) {
            for (int width = 0; width < matrix.length; width++) {
                builder.append(" " + matrix[width][height] + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int[][] getMatrix() {
        return matrix;
    }
}

package math;


import config.Configuration;
import parmutate.ShuffleStrategy;
import random.MersenneTwisterFast;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Matrix implements Comparable<Matrix> {

    int matrix[][] = new int[Configuration.instance.matrixWidth][Configuration.instance.matrixHeight];

    private Matrix() {}

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isValid() {
        int value = sumColumn(0);

        for (int i = 0; i < Configuration.instance.matrixWidth; i++) {
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
        for (int i = 0; i < Configuration.instance.matrixWidth; i++) {
            sum += sumRow(i);
        }
        return sum;
    }

    public void fillEmpty(int value) {
        for (int width = 0; width < matrix.length; width++) {
            for (int height = 0; height < matrix.length; height++) {
                if (matrix[width][height] == 0) {
                    matrix[width][height] = value;
                    return;
                }
            }
        }
    }

    public static Matrix generate() {
        Random random = new MersenneTwisterFast();
        List<TwinPair> pairs = TwinPair.getPairs(Configuration.instance.maxPrimeNumber);
        Collections.shuffle(pairs);
        Matrix matrix = new Matrix();
        int pairCount = Configuration.instance.matrixWidth * Configuration.instance.matrixWidth / 2;
        for (int i = 0; i < pairCount; i++) {
            TwinPair pair = pairs.get(random.nextInt(pairs.size()));
            matrix.fillEmpty(pair.getFirst());
            matrix.fillEmpty(pair.getSecond());
        }

        // Alles durcheinander wirbeln
        matrix = new ShuffleStrategy().doPermutation(matrix);

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

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int compareTo(Matrix matrix) {
        if (getValue() < matrix.getValue()) {
            return -1;
        } else if (getValue() > matrix.getValue()) {
            return 1;
        }
        return 0;
    }
}

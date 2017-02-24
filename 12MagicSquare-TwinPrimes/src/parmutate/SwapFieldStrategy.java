package parmutate;

import math.Matrix;
import random.MersenneTwisterFast;

import java.util.Random;

public class SwapFieldStrategy implements IStrategy {

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @Override
    public Matrix doPermutation(Matrix matrix) {
        int[][] matrixValues = matrix.getMatrix();

        int column1 = random.nextInt(matrixValues.length);
        int column2 = random.nextInt(matrixValues.length);

        int row1 = random.nextInt(matrixValues.length);
        int row2 = random.nextInt(matrixValues.length);

        int tmp = matrixValues[column1][row1];
        matrixValues[column1][row1] = matrixValues[column2][row2];
        matrixValues[column2][row2] = tmp;

        matrix.setMatrix(matrixValues);

        return matrix;
    }
}

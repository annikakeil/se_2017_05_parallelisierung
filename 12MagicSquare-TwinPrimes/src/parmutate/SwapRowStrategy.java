package parmutate;

import math.Matrix;
import random.MersenneTwisterFast;

import java.util.Random;

public class SwapRowStrategy implements IStrategy {

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @Override
    public Matrix doPermutation(Matrix matrix) {
        int[][] matrixValues = matrix.getMatrix();

        int row1 = random.nextInt(matrixValues.length);
        int row2 = random.nextInt(matrixValues.length);

        for (int i = 0; i < matrixValues.length; i++) {
            int tmp = matrixValues[i][row1];
            matrixValues[i][row1] = matrixValues[i][row2];
            matrixValues[i][row2] = tmp;
        }

        matrix.setMatrix(matrixValues);

        return matrix;
    }
}

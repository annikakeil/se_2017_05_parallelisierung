package parmutate;

import math.Matrix;
import random.MersenneTwisterFast;

import java.util.Random;

public class SwapColumnStrategy implements IStrategy {

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @Override
    public Matrix doPermutation(Matrix matrix) {
        int[][] matrixValues = matrix.getMatrix();

        int column1 = random.nextInt(matrixValues.length);
        int column2 = random.nextInt(matrixValues.length);

        int[] tmp = matrixValues[column1];
        matrixValues[column1] = matrixValues[column2];
        matrixValues[column2] = tmp;

        matrix.setMatrix(matrixValues);

        return matrix;
    }
}

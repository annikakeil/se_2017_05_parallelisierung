package parmutate;

import math.Matrix;

import java.util.Random;

public class SwapDiagonalStrategy implements IStrategy {
    @Override
    public Matrix doPermutation(Matrix matrix) {
        int[][] matrixValues = matrix.getMatrix();

        int[] values = new int[matrixValues.length];

        for (int i = 0; i < matrixValues.length; i++) {
            values[i] = matrixValues[i][i];
        }

        for (int i = 0; i < matrixValues.length; i++) {
            matrixValues[i][i] = matrixValues[matrixValues.length - 1 - i][i];
        }

        for (int i = 0; i < matrixValues.length; i++) {
            matrixValues[matrixValues.length - 1 - i][i] = values[i];
        }

        matrix.setMatrix(matrixValues);

        return matrix;
    }
}

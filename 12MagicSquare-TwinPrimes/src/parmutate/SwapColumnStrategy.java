package parmutate;

import math.Matrix;

import java.util.Random;

public class SwapColumnStrategy implements IStrategy {
    @Override
    public Matrix doPermutation(Matrix matrix) {
        Random random = new Random();

        int[][] matrixValue = matrix.getMatrix();

        int column1 = random.nextInt(matrixValue.length);
        int column2 = random.nextInt(matrixValue.length);

        int[] tmp = matrixValue[column1];
        matrixValue[column1] = matrixValue[column2];
        matrixValue[column2] = tmp;

        return new Matrix(matrixValue);
    }
}

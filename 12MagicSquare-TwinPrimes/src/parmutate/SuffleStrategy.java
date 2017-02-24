package parmutate;

import math.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuffleStrategy implements IStrategy {
    @Override
    public Matrix doPermutation(Matrix matrix) {
        List<int[]> matrixValues = new ArrayList<int[]>();
        matrixValues.addAll(Arrays.asList(matrix.getMatrix()));

        int[][] newMatrixValues = new int[matrixValues.size()][matrixValues.size()];
        Collections.shuffle(matrixValues);
        matrixValues.toArray(newMatrixValues);
        return new Matrix(newMatrixValues);
    }
}

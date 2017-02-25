package parmutate;

import math.Matrix;
import random.MersenneTwister;
import random.MersenneTwisterFast;

import java.util.*;

public class ShuffleStrategy implements IStrategy {

    private Random twister = new MersenneTwisterFast();

    @Override
    public Matrix doPermutation(Matrix matrix) {
        int[][] matrixValues = matrix.getMatrix();

        List<Integer> values = new ArrayList<Integer>();
        for (int width = 0; width < matrixValues.length; width++) {
            for (int height = 0; height < matrixValues.length; height++) {
                values.add(matrixValues[width][height]);
            }
        }

        Collections.shuffle(values, twister);

        int index = 0;
        for (int width = 0; width < matrixValues.length; width++) {
            for (int height = 0; height < matrixValues.length; height++) {
                matrixValues[width][height] = values.get(index);
                index++;
            }
        }

        matrix.setMatrix(matrixValues);

        return matrix;
    }
}

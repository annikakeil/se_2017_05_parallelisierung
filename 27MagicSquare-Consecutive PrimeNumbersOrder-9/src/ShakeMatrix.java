import java.util.ArrayList;
import java.util.Collections;

public class ShakeMatrix implements IStrategy{

    @Override
    public Matrix doStrategy(Matrix m) {
        int[][] matrix = m.getMatrix();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for (int i = 0; i < m.getSize(); i++){
            for (int j = 0; j < m.getSize(); j++){
                temp.add(matrix[i][j]);
            }
        }

        Collections.shuffle(temp, new MersenneTwister());

        int t = 0;
        for (int i = 0; i < m.getSize(); i++){
            for (int j = 0; j < m.getSize(); j++){
                matrix[i][j] = temp.get(t);
                t++;
            }
        }

        return new Matrix(matrix);
    }
}

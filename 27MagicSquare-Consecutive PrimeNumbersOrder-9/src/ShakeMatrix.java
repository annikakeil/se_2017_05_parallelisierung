import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class ShakeMatrix implements IStrategy{

    @Override
    public Matrix doStrategy(Matrix m) {
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for (int i = 0; i < m.getSize(); i++){
            for (int j = 0; j < m.getSize(); j++){
                temp.add(m.matrix[i][j]);
            }
        }

        Collections.shuffle(temp); // TODO MersenneTwister

        int t = 0;
        for (int i = 0; i < m.getSize(); i++){
            for (int j = 0; j < m.getSize(); j++){
                m.matrix[i][j] = temp.get(t);
                t++;
            }
        }

        return m;
    }
}

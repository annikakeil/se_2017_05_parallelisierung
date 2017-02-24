
public class SwapDiagonal implements IStrategy{


    @Override
    public Matrix doStrategy(Matrix m) {
        int[] rl = new int[m.getSize()];

        for (int i = 0; i < m.getSize(); i++){
            rl[i] = m.matrix[i][i];
        }

        for (int j = 0; j < m.getSize(); j++){
            m.matrix[j][j] = m.matrix[j][m.getSize() -1 -j];
        }

        for (int k = 0; k < m.getSize(); k++){
            m.matrix[k][m.getSize() -1 -k] = rl[k];
        }
        return m;
    }
}

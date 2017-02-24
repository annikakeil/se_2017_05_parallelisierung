import math.Matrix;
import math.TwinPair;
import parmutate.IStrategy;
import parmutate.SwapColumnStrategy;

public class Application {
    public static void main(String[] args) {
        for (TwinPair pair : TwinPair.getPairs(1000)) {
            //System.out.println(pair);
        }

        Matrix matrix = Matrix.generate();

        System.out.println(matrix);

        IStrategy s = new SwapColumnStrategy();
        matrix = s.doPermutation(matrix);

        System.out.println(matrix);


        for (int i = 0; i < 50000; i++) {
            //System.out.println(Matrix.generate());
        }
    }
}
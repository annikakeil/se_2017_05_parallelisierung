public class SwapLines  implements IStrategy{

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @Override
    public Matrix doStrategy(Matrix m) {

        int[][] matrix = m.getMatrix();

        int line1 = random.nextInt(matrix.length);
        int line2 = 0;
        do {
            line2 = random.nextInt(matrix.length);
        } while (line1 == line2);

        int[] temp = matrix[line1];
        matrix[line1] = matrix[line2];
        matrix[line2] = temp;

        return new Matrix(matrix);
    }
}

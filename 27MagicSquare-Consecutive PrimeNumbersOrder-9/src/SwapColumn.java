public class SwapColumn implements IStrategy {

    private MersenneTwisterFast random = new MersenneTwisterFast();

    @Override
    public Matrix doStrategy(Matrix matrix) {

        int columnIndex1 = random.nextInt(matrix.getMatrix().length);
        int columnIndex2 = 0;
        do {
            columnIndex2 = random.nextInt(matrix.getMatrix().length);
        } while (columnIndex1 == columnIndex2);

        int[] column1 = matrix.getRow(columnIndex1);
        int[] column2 = matrix.getRow(columnIndex2);

        matrix.setRow(columnIndex1, column2);
        matrix.setRow(columnIndex2, column1);

        return matrix;
    }
}

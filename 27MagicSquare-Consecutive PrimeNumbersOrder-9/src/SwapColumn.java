public class SwapColumn implements IStrategy{

    @Override
    public Matrix doStrategy(Matrix m) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        int columnIndex1 = random.nextInt(m.matrix.length);
        int columnIndex2 = random.nextInt(m.matrix.length);

        int[] column1 = m.getRow(columnIndex1);
        int[] column2 = m.getRow(columnIndex2);

        m.setRow(columnIndex1, column2);
        m.setRow(columnIndex2, column1);

        return m;
    }
}

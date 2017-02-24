public class SwapLines  implements IStrategy{

    @Override
    public Matrix doStrategy(Matrix m) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        int line1 = random.nextInt(m.matrix.length);
        int line2 = random.nextInt(m.matrix.length);

        int[] temp = m.matrix[line1];
        m.matrix[line1] = m.matrix[line2];
        m.matrix[line2] = temp;

        return m;
    }
}

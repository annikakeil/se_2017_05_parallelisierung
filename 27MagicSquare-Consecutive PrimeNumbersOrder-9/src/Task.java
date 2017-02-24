public class Task implements Runnable {

    private Matrix matrix = Matrix.generate(9, 1000);

    private void doStrategy() {
        MersenneTwisterFast random = new MersenneTwisterFast();
        float value = random.nextFloat();
        IStrategy strategy;
        if (value < 0.5) {
            strategy = new ShakeMatrix();
        } else if (value < 0.67) {
            strategy = new SwapColumn();
        } else if(value < 0.84) {
            strategy = new SwapLines();
        } else {
            strategy = new SwapDiagonal();
        }

        matrix = strategy.doStrategy(matrix);
    }

    @Override
    public void run() {
        int iteraton = 0;
        while (iteraton < Configuration.instance.maxIterationsEachThread && !matrix.isMagic()) {
            doStrategy();
            iteraton++;
        }

        if (matrix.isMagic()) {
            System.out.println(matrix);
        }
    }
}
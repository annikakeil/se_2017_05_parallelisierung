import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task implements Runnable {

    private Matrix matrix = Matrix.generate();
    private CyclicBarrier cyclicBarrier;
    private Application application;
    private MersenneTwisterFast random = new MersenneTwisterFast();
    private IStrategy shakeStrategy = new ShakeMatrix();
    private IStrategy columnStrategy = new SwapColumn();
    private IStrategy lineStrategy = new SwapLines();
    private IStrategy diagonalStrategy = new SwapDiagonal();

    public Task(Application application, CyclicBarrier cyclicBarrier) {
        this.application = application;
        this.cyclicBarrier = cyclicBarrier;
    }


    private void doStrategy() {

        float value = random.nextFloat();
        IStrategy strategy;
        if (value < Configuration.instance.probShake) {
            strategy = shakeStrategy;
        } else if (value < (Configuration.instance.probColumn + Configuration.instance.probShake)) {
            strategy = columnStrategy;
        } else if(value < (Configuration.instance.probLine + (Configuration.instance.probColumn + Configuration.instance.probShake))) {
            strategy = lineStrategy;
        } else {
            strategy = diagonalStrategy;
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
            application.foundMagic(matrix);
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        } catch (BrokenBarrierException bbex) {
            System.out.println(bbex.getMessage());
        }
    }
}
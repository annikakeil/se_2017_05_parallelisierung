import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Application {

    public static void main(String[] args) {
        System.out.println(PrimeGenerator.createPrimeList());

        Application application = new Application();
        application.execute();
    }

    private boolean hasMagicFound = false;

    public void execute() {
        for (int i = 0; i < (1000 / Configuration.instance.threadCount); i++) {

            final CyclicBarrier cyclicBarrier = new CyclicBarrier(Configuration.instance.threadCount);

            List<Thread> threads = new ArrayList<Thread>();

            for (int tID = 0; tID < Configuration.instance.threadCount; tID++) {
                Thread thread = new Thread(new Task(this, cyclicBarrier));
                thread.start();
                threads.add(thread);

                System.out.println("Starting Thread[" + i + "]" + tID);
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (hasMagicFound) {
                return;
            }
        }
    }

    public void foundMagic(Matrix matrix) {
        this.hasMagicFound = true;
        System.out.println(matrix);
    }
}
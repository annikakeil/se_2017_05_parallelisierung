import math.Matrix;
import task.ITaskListener;
import task.Task;
import task.TaskMonitor;
import task.TaskObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Application {

    private TaskObserver taskObserver = new TaskObserver();
    private TaskMonitor taskMonitor = new TaskMonitor();

    public Application() {
        taskMonitor.addListener(taskObserver);
    }

    public void execute() {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < (1000 / cpuCores); i++) {
            List<Thread> threads = new ArrayList<Thread>();

            final CyclicBarrier cyclicBarrier = new CyclicBarrier(cpuCores);

            for (int core = 0; core < cpuCores; core++) {
                Thread thread = new Thread(new Task(taskMonitor, cyclicBarrier));
                thread.start();
                threads.add(thread);
                System.out.println("Starting Thread [" + i + "]: " + core);
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void print() {
        for (Matrix matrix : taskObserver.getMatrix()) {
            System.out.println("Matix:");
            System.out.println(matrix);
            System.out.println("Matrix-Value:" + matrix.getValue());
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        app.execute();
        app.print();
    }
}
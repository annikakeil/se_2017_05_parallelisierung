import config.Configuration;
import math.Matrix;
import task.Task;
import task.TaskMonitor;
import task.TaskObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

public class Application {

    private TaskObserver taskObserver = new TaskObserver();
    private TaskMonitor taskMonitor = new TaskMonitor();

    public Application() {
        taskMonitor.addListener(taskObserver);
    }

    public void execute() {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < (Configuration.instance.threadsCount / cpuCores); i++) {
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
        Matrix matrix = taskObserver.getResults().get(0);
        System.out.println("Best Matix:");
        System.out.println(matrix);
        System.out.println("Matrix-Value:" + matrix.getValue());
        System.out.println();

        System.out.println("Matrix Count: " + taskObserver.getResults().size());
        System.out.println(
                "Staistics:" +
                taskObserver.getResults().stream().collect(Collectors.summarizingInt((r) -> r.getValue())) + " " + taskObserver.getResults().size()
        );
    }

    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        app.execute();
        app.print();
    }
}
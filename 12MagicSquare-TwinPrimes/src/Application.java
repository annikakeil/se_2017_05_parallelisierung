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
                //System.out.println("Starting Thread [" + i + "]: " + core);
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
        /*for (Matrix matrix : taskObserver.getResults()) {
            System.out.println("Matix:");
            System.out.println(matrix);
            System.out.println("Matrix-Value:" + matrix.getValue());
            System.out.println();
        }

        System.out.println("Count: " + taskObserver.getResults().size());*/
        System.out.println(
                taskObserver.getResults().stream().collect(Collectors.summarizingInt((r) -> r.getValue()))
        );
    }

    public static void run() {
        for (int i = 0; i < 10; i++) {
            Application app = new Application();
            app.execute();
            app.print();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();

        for (int i = 0; i < 1000; i++) {
            Matrix m = Matrix.generate();
        }

        System.out.println("Duration: " + (new Date().getTime() - date.getTime()));

        Configuration.instance.threadIterationsEachMatrix = 10;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();


        Configuration.instance.threadIterationsEachMatrix = 100;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();


        Configuration.instance.threadIterationsEachMatrix = 500;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();


        Configuration.instance.threadIterationsEachMatrix = 1000;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();

        Configuration.instance.threadIterationsEachMatrix = 10000;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();

        Configuration.instance.threadIterationsEachMatrix = 100000;
        System.out.println("Iterations Each Matrix: " + Configuration.instance.threadIterationsEachMatrix);
        run();
    }
}
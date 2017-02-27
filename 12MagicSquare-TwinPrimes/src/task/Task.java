package task;

import config.Configuration;
import math.Matrix;
import parmutate.*;
import random.MersenneTwisterFast;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private MersenneTwisterFast random = new MersenneTwisterFast();
    private Matrix matrix;
    private TaskMonitor monitor;

    private ShuffleStrategy suffleStrategy = new ShuffleStrategy();
    private SwapDiagonalStrategy swapDiagonalStrategy = new SwapDiagonalStrategy();
    private SwapColumnStrategy swapColumnStrategy = new SwapColumnStrategy();
    private SwapRowStrategy swapRowStrategy = new SwapRowStrategy();
    private SwapFieldStrategy swapFieldStrategy = new SwapFieldStrategy();

    public Task(TaskMonitor monitor, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.monitor = monitor;

        matrix = Matrix.generate();
    }

    private void doStragety() {
        IStrategy strategy;

        float value = random.nextFloat();
        if (value < 0.05) {
            strategy = swapFieldStrategy;
        } else if (value < 0.1) {
            strategy = swapDiagonalStrategy;
        } else if (value < 0.15) {
            strategy = swapColumnStrategy;
        } else if (value < 0.2) {
            strategy = swapRowStrategy;
        } else {
            strategy = suffleStrategy;
        }

        matrix = strategy.doPermutation(matrix);
    }

    @Override
    public void run() {
        int destination = Configuration.instance.threadIterationCount / Configuration.instance.threadIterationsEachMatrix;
        for (int j = 0; j < destination; j++) {
            int i = 0;

            while (i < Configuration.instance.threadIterationsEachMatrix && !matrix.isValid()) {
                doStragety();
                i++;
            }

            if (matrix.isValid()) {
                monitor.trigger(matrix);
            }

            matrix = Matrix.generate();
        }

        try {
            this.cyclicBarrier.await();
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        } catch (BrokenBarrierException bbex) {
            System.out.println(bbex.getMessage());
        }
    }
}
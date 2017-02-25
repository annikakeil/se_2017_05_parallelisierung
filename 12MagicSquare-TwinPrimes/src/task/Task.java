package task;

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
        if (value < 0.1) {
            strategy = swapFieldStrategy;
        } else if (value < 0.2) {
            strategy = swapDiagonalStrategy;
        } else if (value < 0.3) {
            strategy = swapColumnStrategy;
        } else if (value < 0.4) {
            strategy = swapRowStrategy;
        } else {
            strategy = suffleStrategy;
        }

        matrix = strategy.doPermutation(matrix);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            int i = 0;

            while (i < 100 && !matrix.isValid()) {
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
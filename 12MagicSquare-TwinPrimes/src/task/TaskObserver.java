package task;

import math.Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskObserver implements ITaskListener {

    private List<Matrix> matrizen = new ArrayList<Matrix>();
    private final Lock _mutex = new ReentrantLock(true);

    @Override
    public void foundSolution(Matrix matrix) {
        _mutex.lock();
        matrizen.add(matrix);
        _mutex.unlock();
    }

    public List<Matrix> getResults() {
        Collections.sort(matrizen);
        return matrizen;
    }
}

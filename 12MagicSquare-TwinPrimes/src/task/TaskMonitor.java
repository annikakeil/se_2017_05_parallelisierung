package task;

import math.Matrix;

import java.util.ArrayList;
import java.util.List;

public class TaskMonitor {
    public List<ITaskListener> listeners = new ArrayList<ITaskListener>();

    public void addListener(ITaskListener listener) {
        listeners.add(listener);
    }

    public void trigger(Matrix matrix) {
        for (ITaskListener listener : listeners) {
            listener.foundSolution(matrix);
        }
    }
}

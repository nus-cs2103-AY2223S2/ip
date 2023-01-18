package services;

import types.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskStorage {
    private final ArrayList<Task> tasks = new ArrayList<>(50);

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }
}

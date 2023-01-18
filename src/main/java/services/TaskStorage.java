package services;

import types.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TaskStorage {
    private final ArrayList<Task> tasks = new ArrayList<>(50);

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void markByNo(int n) {
        this.tasks.get(n - 1).setDone();
    }

    public void unmarkByNo(int n) {
        this.tasks.get(n - 1).setUndone();
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTaskByNo(int n) {
        return this.tasks.get(n - 1);
    }
}

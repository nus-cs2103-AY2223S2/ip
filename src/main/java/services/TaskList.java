package services;

import types.IContainer;
import types.data.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>(50);
    private final IContainer<Task> taskContainer;

    public TaskList(IContainer<Task> c) {
        taskContainer = c;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        taskContainer.add(t);
        taskContainer.flush();
    }

    public void markByNo(int n) {
        this.tasks.get(n - 1).setDone();
        taskContainer.push(getTasks());
    }

    public void unmarkByNo(int n) {
        this.tasks.get(n - 1).setUndone();
        taskContainer.push(getTasks());
    }

    public void deleteByNo(int n) {
        this.tasks.remove(n - 1);
        taskContainer.push(getTasks());
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

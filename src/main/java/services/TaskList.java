package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import types.IContainer;
import types.data.Task;

/**
 * A singleton class to host tasks instance-wide.
 */
public final class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>(50);
    private final IContainer<Task> taskContainer;

    public TaskList(IContainer<Task> c) {
        taskContainer = c;
    }

    /**
     * Add new Task into the list.
     * @param t Task to add.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        taskContainer.add(t);
        taskContainer.flush();
    }

    /**
     * Mark some task as completed. Index from 1.
     * @param n Task number to mark.
     */
    public void markByNo(int n) {
        this.tasks.get(n - 1).setDone();
        taskContainer.push(getTasks());
    }

    /**
     * Mark some task as incomplete. Index from 1.
     * @param n Task number to unmark.
     */
    public void unmarkByNo(int n) {
        this.tasks.get(n - 1).setUndone();
        taskContainer.push(getTasks());
    }

    /**
     * Delete some task.
     * @param n Task number to delete.
     */
    public void deleteByNo(int n) {
        this.tasks.remove(n - 1);
        taskContainer.push(getTasks());
    }

    /**
     * Retrieves a read-only copy of current task list.
     * @return List of task.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Retrieves number of tasks.
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves some task. Index from 1.
     * @param n Task number to get.
     * @return The task at given location.
     */
    public Task getTaskByNo(int n) {
        return this.tasks.get(n - 1);
    }
}

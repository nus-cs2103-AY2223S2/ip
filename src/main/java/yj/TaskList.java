package yj;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     * @param task Task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     * @param i Index of task
     * @return Task at index i
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index.
     * @param i Index of task
     * @return Task at index i
     */
    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks
     */
    public int getNumberofTasks() {
        return tasks.size();
    }
}

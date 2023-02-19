package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList class that stores tasks.
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Constructor to create a TaskList.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of the TaskList
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Returns the task at the specified index in the TaskList.
     *
     * @param index The specified index of the task to be returned.
     * @return Task at specified index.
     */
    public Task get(int index) {
        return tasklist.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasklist.add(task);
    }

    /**
     * Adds a task to the TaskList at a specified index.
     *
     * @param index The specified index.
     * @param task The task to be added.
     */
    public void add(int index, Task task) {
        tasklist.add(index, task);
    }

    /**
     * Removes the task at the specified index in the TaskList and returns it.
     *
     * @param index The specified index.
     * @return The task removed.
     */
    public Task remove(int index) {
        return tasklist.remove(index);
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return Boolean representing if the TaskList is empty.
     */
    public boolean isEmpty() {
        return tasklist.isEmpty();
    }
}

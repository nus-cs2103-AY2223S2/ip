package task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a list of Tasks for Duke.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor for TaskList when no Tasks are present.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList when Tasks are present.
     *
     * @param inputTasks Array of Tasks to be added.
     */
    public TaskList(Task[] inputTasks) {
        this();
        tasks.addAll(Arrays.asList(inputTasks));
    }

    /**
     * Adds a Task to existing TaskList.
     *
     * @param task Task to be added to current list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from list at given index.
     *
     * @param i Index where task is to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Returns a task at a given index.
     *
     * @param i Index where task is to be returned.
     * @return The task requested by Duke.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the number of items in the existing TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an array of tasks stored in current TaskList.
     *
     * @return An array of Tasks from current TaskList.
     */
    public Task[] asList() {
        return tasks.toArray(new Task[0]);
    }
}

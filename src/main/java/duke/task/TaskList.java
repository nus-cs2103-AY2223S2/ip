package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A wrapper class for arraylist of tasks
 * @author Junyi
 */
public class TaskList {
    /* An ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * Defaults to an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * Fills list with tasks from given array.
     * @param taskArr The mentioned array.
     */
    public TaskList(Task[] taskArr) {
        this();

        tasks.addAll(Arrays.asList(taskArr));
        Collections.sort(tasks);
    }

    /**
     * Returns the number of tasks. Does not include deleted tasks.
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds the task into the list.
     * @param task The mentioned task.
     */
    public void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
    }

    /**
     * Returns the task corresponding to the index in the list.
     * @param index Index of task in the list.
     * @return The mentioned task in the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task corresponding to the index in the list.
     * @param index Index of task in the list.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns an array of tasks in the task list.
     * @return Array of tasks.
     */
    public Task[] allTasks() {
        return tasks.toArray(new Task[0]);
    }

}

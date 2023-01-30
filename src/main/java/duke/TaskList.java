package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Tasklist object that allows abstraction to track and edit the list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private final ArrayList<Task> TASKS;

    /**
     * Constructor for a new empty list of tasks.
     */
    public TaskList() {
        this.TASKS = new ArrayList<Task>();
    }

    /**
     * Constructor for a list of tasks with those provided.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks
     */
    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    /**
     * Adds the provided task into the list of tasks.
     *
     * @param task New task to be added into the list
     */
    public void addTask(Task task) {
        this.TASKS.add(task);
    }

    /**
     * Returns the number of tasks in the list of tasks.
     *
     * @return The number of tasks in the list
     */
    public int size() {
        return this.TASKS.size();
    }
}

package book;

import java.util.ArrayList;

import book.task.Task;

/**
 * Implementation of the list of {@code Task}s in {@code Book}.
 */
public class TaskList {
    /** {@code ArrayList<Task>} storing the {@code Task}s in {@code Book}. */
    private static ArrayList<Task> list;

    /**
     * Initializes a {@code TaskList} with the given {@code ArrayList<Task>}.
     * @param list {@code ArrayList<Task>} to initialize the {@code TaskList} with.
     */

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Initializes an empty {@code TaskList}.
     */
    public TaskList() {
        this(new ArrayList<Task>(100));
    }

    /**
     * Adds the given {@code Task} to the {@code TaskList}.
     * @param task {@code Task} to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the {@code Task} at the given {@code int} index.
     * @param index {@code int} index of {@code Task} to retrieve.
     * @return The {@code Task} at the given {@code int} index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Removes the {@code Task} associated with the given {@code int} index from the
     * {@code TaskList}.
     * @param index {@code int} index of {@code Task} to remove.
     * @return The removed {@code Task} at the given {@code int} index.
     */
    public Task deleteTask(int index) {
        return this.list.remove(index);
    }

    /**
     * Returns the {@code int} size of the {@code TaskList}.
     * @return the {@code int} size of the {@code TaskList}.
     */
    public int listSize() {
        return this.list.size();
    }

    /**
     * Returns the {@code String} representation of the {@code TaskList}.
     * @return {@code String} representation of the {@code TaskList}.
     */
    @Override
    public String toString() {
        int index = 1;
        String listString = "";
        for (Task task : this.list) {
            listString += index++ + ".  " + task + "\n";
        }
        return listString;
    }
}

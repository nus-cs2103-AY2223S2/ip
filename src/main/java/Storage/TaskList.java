package Storage;

import java.util.ArrayList;

import Exceptions.SelectOutOfIndexException;
import Tasks.Task;

/**
 * This class is used to store all the tasks.
 */
public class TaskList {
    private ArrayList<Task> db;

    /**
     * Constructor for the TaskList.
     * @param db The database.
     */
    public TaskList(ArrayList<Task> db) {
        this.db = db;
    }

    /**
     * Add a task into the database.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        db.add(task);
    }

    /**
     * Remove a task from the database based on the index.
     * @param ind The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task removeTaskByIndex(int ind) {
        if (ind >= this.db.size()) {
            throw new SelectOutOfIndexException(null);
        }
        return this.db.remove(ind);
    }

    /**
     * Returns the task from the database based on the index.
     * @param ind The index of the task to be retrieved.
     * @return The task based on the index.
     */
    public Task getTaskByIndex(int ind) {
        if (ind >= this.db.size()) {
            throw new SelectOutOfIndexException(null);
        }
        return this.db.get(ind);
    }

    /**
     * Returns the number of tasks in the database.
     * @return The number of tasks in the database.
     */
    public int getSize() {
        return this.db.size();
    }

    /**
     * Returns the database.
     * @return The database.
     */
    protected ArrayList<Task> getDb() {
        return this.db;
    }

    /**
     * Checks if the database is empty.
     * @return A boolean value based on whether the database is empty.
     */
    public boolean isEmpty() {
        return this.db.isEmpty();
    }

    /**
     * Returns all the tasks iteratively in string representation.
     * @return The tasks in string representation.
     */
    public String getTasks() {
        String str = "";
        for (int i = 1; i <= this.db.size(); i++) {
            str += (i + "." + this.db.get(i - 1) + "\n");
        }
        return str.substring(0, str.length() - 1);
    }
}

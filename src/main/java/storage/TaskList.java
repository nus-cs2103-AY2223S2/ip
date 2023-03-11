package storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.SelectOutOfIndexException;
import tasks.Task;

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
     * Checks if the database is empty.
     * @return A boolean value based on whether the database is empty.
     */
    public boolean isEmpty() {
        return this.db.isEmpty();
    }

    /**
     * Returns all the tasks in an unmodifiable list.
     * @return All tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.db);
    }

    /**
     * Returns all the matching tasks in an unmodifiable list.
     * @param description The description to be matched.
     * @return All matching tasks.
     */
    public List<Task> getMatchingTasks(String description) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 1; i <= this.db.size(); i++) {
            if (this.db.get(i - 1).getDescription().contains(description)) {
                temp.add(this.db.get(i - 1));
            }
        }
        return Collections.unmodifiableList(temp);
    }
}

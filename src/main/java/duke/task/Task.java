package duke.task;

import java.util.ArrayList;

/**
 * Represents the abstract Task class that all tasks in Duke inherit from.
 */
public abstract class Task {

    /** true when the task is completed, false if not. */
    public boolean isCompleted;

    /** Details of the task. */
    public String details;

    /** Type of task. */
    public Types type;

    /** Generic constructor, defaults as false for isCompleted and takes in the task details. */
    public Task(String task) {
        this.isCompleted = false;
        this.details = task;
    }

    public abstract String status();

    /** Marks the task as complete. */
    public void complete() {
        this.isCompleted = true;
    }

    /** Marks the task as incomplete */
    public void incomplete() {
        this.isCompleted = false;
    }

    /**
     * @return all relevant information of the task in an ArrayList of Strings to be saved into the Database.
     */
    public abstract ArrayList<String> data();
}

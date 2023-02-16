package duke.task;

import java.io.Serializable;

/**
 * This Task class represents a task.
 */
public class Task implements Serializable, Comparable<Task> {
    // Unique identifier for Serializer implementation: do not change var name
    private static final long serialVersionUID = 100;
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task with a name.
     *
     * @param name The name of a task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @return True when marked done.
     */
    public boolean setAsDone() {
        return this.isDone = true;
    }

    /**
     * Unmarks the task, making the task not done.
     *
     * @return True when successfully unmarked.
     */
    public boolean setAsNotDone() {
        return !(this.isDone = false);
    }

    /**
     * Gets the done status of the task.
     *
     * @return True if done and false if not done.
     */
    public boolean isMarkedDone() {
        return this.isDone;
    }

    /**
     * Informs the user if the task name contains the string s.
     *
     * @param s The string to check if the name contains.
     * @return True if string s is found in the name, false otherwise.
     */
    public boolean hasStringInName(String s) {
        return name.contains(s);
    }

    /**
     * Informs the user if the task has a set time.
     *
     * @return True if it has a set time, false otherwise.
     */
    public boolean hasDate() {
        return false;
    }

    /**
     * Compares this tasks with the specified task for order of importance.
     *
     * <p>
     * Returns a negative integer, zero, or a positive integer if this object is more important than, equal
     * to, or less important than the specified object.
     *
     * <pre>
     * Order of importance determination:
     * 1. Unfinished Tasks are More Important than Completed Tasks.
     * 2. Dated Unfinished Tasks are More Important than Undated Unfinished Tasks.
     * 3. Unfinished Earlier End Dates are More Important than Unfinished Later End Dates.
     * </pre>
     *
     * @param other The task to compare to.
     */
    @Override
    public int compareTo(Task other) {
        // done tasks are equally unimportant
        if (other.isMarkedDone() && this.isMarkedDone()) {
            return 0;
        }

        if (!other.isMarkedDone() && !this.isMarkedDone()) {
            if (other.hasDate()) {
                return 1;
            }

            // we are equally unimportant
            return 0;
        }

        return this.isMarkedDone() ? 1 : -1;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.name : "[ ] " + this.name;
    }

}

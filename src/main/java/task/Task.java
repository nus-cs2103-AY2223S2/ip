package task; /**
 * The base class for all tasks
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * The default constructor
     */
    public Task() {
        this("");
    }

    /**
     * The task string description
     *
     * @param description: a string describing the task
     */
    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * Parse the date into a date object
     *
     * @param dateString: the string representation of the date
     * @return the date object
     */
    protected static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    /**
     * Gives the string representation of date
     *
     * @param date: a date object
     * @return the corresponding string representation
     */
    protected static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get the name of the task
     *
     * @return the string name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get whether the task is done
     *
     * @return the status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark this task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as undone
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Get the string icon of the status of the task
     *
     * @return the string icon of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Overriding
     *
     * @return the task string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}

package task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The abstract class Task represents a task with a description and completion status.
 * This class provides basic properties and methods for all task subclasses.
 *
 * @author Tan Yan-Hao Joshua
 */
public abstract class Task {

    /**
     * The description of the Task.
     */
    private String description;

    /**
     * The readFormat attribute is a {@link SimpleDateFormat} object used to parse the date input.
     */
    private DateFormat readFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * The writeFormat attribute is a {@link SimpleDateFormat} object used to format the date output.
     */
    private DateFormat writeFormat = new SimpleDateFormat("E, MMM dd yyyy, h:mm aa");

    /**
     * The isComplete attribute stores the completion status of a task.
     * A task is considered complete if the isComplete value is set to true,
     * otherwise, it is considered incomplete if the isComplete value is set to false.
     */
    private boolean isComplete;

    /**
     * Constructor for Task class
     *
     * @param description String to describe the task
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Boolean indicating if the task is complete.
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Mark the task as complete.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Mark the task as incomplete.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Abstract method to save the task as a string representation.
     *
     * @return String representation of the task.
     */
    public abstract String save();

    /**
     * Returns the format that Dates are read.
     *
     * @return DateFormat for reading Dates.
     */
    public DateFormat getReadFormat() {
        return this.readFormat;
    }

    /**
     * Returns the format that Dates are written.
     *
     * @return DateFormat for writing Dates.
     */
    public DateFormat getWriteFormat() {
        return this.writeFormat;
    }

    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[] ");
        sb.append(this.description);
        if (this.isComplete) {
            sb.insert(1, "X");
        }
        return sb.toString();
    }
}

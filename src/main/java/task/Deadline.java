package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a Deadline.
 * Extends from Task.
 */
public class Deadline extends Task {
    protected DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String by;
    protected LocalDateTime byDate;
    protected String displayBy;

    /**
     * Constructs a Deadline object.
     * To be used when a new entry is added.
     *
     * @param description String to accompany the task describing the task.
     * @param by String to represent the due date.
     * @return Deadline Object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        byDate = LocalDateTime.parse(by, format);
        displayBy = this.byDate.toString().replace("T", " ");
    }

    /**
     * Constructs a Deadline object.
     * To be used when constructing object from file.
     *
     * @param isDone boolean to represent if object is marked as done.
     * @param description String to accompany the task describing the task.
     * @param by String to represent the due date.
     * @return Deadline Object.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        byDate = LocalDateTime.parse(by, format);
        displayBy = this.byDate.toString().replace("T", " ");
    }

    /**
     * Returns String representation of deadline Object.
     *
     * @return String representation of deadline Object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + displayBy + ")";
    }

    /**
     * Returns String that is a command that can be used to create a similar Deadline Object.
     *
     * @return String that is a command that can be used to create a similar Deadline Object.
     */
    @Override
    public String getCommand() {
        return this.isDone
            ? "1 deadline " + this.description + " /by " + this.by
            : "0 deadline " + this.description + " /by " + this.by;
    }
}

package wtd.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /** The date of the deadline. */
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * 
     * @param description the description of the deadline.
     * @param by the date of the deadline.
     * @param isDone whether the deadline is done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    
    /**
     * Formats the deadline for user output.
     * 
     * @return the string representation of the deadline for user output.
     */
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Formats the deadline for file output.
     * 
     * @return the string representation of the deadline for file output.
     */
    public String toFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}

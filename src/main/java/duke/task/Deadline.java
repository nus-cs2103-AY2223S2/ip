package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Deadline task with an end date.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDateTime dateline;

    /**
     * Constructor method for Deadline.
     *
     * @param title    title of the Deadline.
     * @param deadline deadline of the Deadline.
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
        try {
            this.dateline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Overloaded constructor method for Deadline.
     * Primarily for when loading on startup.
     *
     * @param title    title of the Deadline.
     * @param deadline deadline of the Deadline.
     * @param done     status of the Deadline.
     */
    public Deadline(String title, String deadline, boolean done) {
        super(title);
        this.deadline = deadline;
        this.type = "[D]";
        this.done = done;
        try {
            this.dateline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Alternative toString method purely for writing to hard drive.
     * Can also be used for testing.
     *
     * @return String representation of the task with all attribute information.
     */
    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.deadline;
    }

    /**
     * Method for getting String representation of Deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateline.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}

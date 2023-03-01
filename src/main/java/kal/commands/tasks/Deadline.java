package kal.commands.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class handles Deadlines and their related operations.
 */
public class Deadline extends Task {
    protected static final String DATE_FORMAT = "MMM dd yyyy";
    private static final String IDENTIFIER = "D";
    protected LocalDate deadline;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the deadline.
     * @param deadline The date of the deadline.
     */
    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the deadline.
     * @param isDone The completion status of the deadline.
     * @param deadline The date of the deadline.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Generates a letter representing the type of task.
     *
     * @return a letter representing the type of this task.
     */
    public String getTaskClass() {
        return Deadline.IDENTIFIER;
    }

    private String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern(Deadline.DATE_FORMAT));
    }

    /**
     * Generates a String to store this task in a local text file.
     *
     * @return A representative String that contains data about the current task.
     */
    public String generateStorageText() {
        return String.format("%s~%s~%s~%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskClass(), this.getStatusIcon(), this.description, this.getDeadline());
    }
}

package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Task.
 */
public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     *
     * @param id the id associated with this Task
     * @param task deadline description
     * @param timeParsed time of deadline
     */
    public Deadline(int id, String task, LocalDate timeParsed) {
        super(id, task);
        this.deadline = timeParsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return this.isDone()
                ? "[D][x] " + this.getTask()
                        + " (Due: "
                        + this.deadline.format(formatter) + ")"
                : "[D][ ] " + this.getTask()
                        + " (Due: "
                        + this.deadline.format(formatter) + ")";
    }

    /**
     * Returns deadline.
     *
     * @return LocalDate of the deadline
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }
}

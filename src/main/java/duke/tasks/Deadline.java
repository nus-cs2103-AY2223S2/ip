package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDateException;

/**
 * Deadline is a task that is due by a specific date/time.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate deadline;

    /**
     * Constructor for Deadline.
     *
     * @param command Description of the Deadline.
     * @param deadline The date/time when the Deadline is due.
     * @throws InvalidDateException If the date is invalid.
     */
    public Deadline(String command, String deadline)
            throws InvalidDateException {
        super(command);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return The string storage representation of the Deadline.
     */
    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[D] | %d | %s | %s",
                done,
                task,
                this.deadline);
    }
}

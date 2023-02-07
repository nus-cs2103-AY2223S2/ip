package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class for tasks which have deadlines to meet.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    /**
     * Constructor for Deadline class.
     * Returns Deadline task with status set as NOT_DONE.
     *
     * @param description String description of Deadline task.
     * @param by Deadline date in the format for LocalDate.
     * @throws DukeException If by is not parsable as LocalDate.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date!");
        }
    }

    /**
     * Constructor for Deadline class to be mainly used by Storage class to load tasks from data.txt file.
     * Returns Deadline task with specified status.
     *
     * @param description String description of Deadline task.
     * @param by Deadline date in the format for LocalDate.
     * @param status Status of the task.
     * @throws DukeException If by is not parsable as LocalDate.
     */
    public Deadline(String description, String by, String status) throws DukeException {
        super(description, status);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for date!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts Deadline data into String to be used to save task data.
     * Returns String of Deadline in a format to be saved and loaded in the future.
     */
    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + this.by;
    }
}

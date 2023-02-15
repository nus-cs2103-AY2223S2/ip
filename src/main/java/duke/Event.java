package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class for tasks which have start and end dates.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructor for Event class.
     * Returns Event task with status set as NOT_DONE.
     *
     * @param description String description of Event task.
     * @param from Event start date in the format for LocalDate.
     * @param to Event end date in the format for LocalDate.
     * @throws DukeException If by is not parsable as LocalDate.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for dates!");
        }

    }

    /**
     * Constructor for Event class to be mainly used by Storage class to load tasks from data.txt file.
     * Returns Event task with specified status.
     *
     * @param description String description of Event task.
     * @param from Event start date in the format for LocalDate.
     * @param to Event end in the format for LocalDate.
     * @param status Status of the task.
     * @throws DukeException If by is not parsable as LocalDate.
     */
    public Event(String description, String from, String to, String status) throws DukeException {
        super(description, status);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for dates!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts Event data into String to be used to save task data.
     * Returns String of Event in a format to be saved and loaded in the future.
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + this.from + "|" + this.to;
    }

}

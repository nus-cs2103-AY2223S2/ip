package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Event is a task that has a from and to date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * A Constructor for Event.
     *
     * @param description Description of Event.
     * @param from Event start date.
     * @param to Event end date.
     * @param isDone Completion status of Event.
     */
    public Event(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("\tPlease enter a valid date in e.g /from yyyy-mm-dd /to yyyy-mm-dd format!");
        }
    }
    public LocalDate getStartDate() {
        return this.from;
    }

    /**
     * A method that converts the Event into its String representation.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "\t[E]" + super.toString()
                + " (from: " + from.format(formatter) + " to: "
                + to.format(formatter) + ")";
    }

    /**
     * Converts the Event into the String format required to be saved in the Storage.
     *
     * @return String formatted data of Event.
     */
    public String saveFormat() {
        return String.format("E | %s | %s to %s", super.saveFormat(), this.from, this.to);
    }
}

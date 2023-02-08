package berry.task;

import berry.exception.IncorrectDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    // The date the task will range from and to
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) throws IncorrectDateException {
        super(description);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing an event task to be saved into the file
     */
    @Override
    public String interpretTaskToText() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
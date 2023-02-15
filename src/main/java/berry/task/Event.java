package berry.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

import berry.exception.IncorrectDateException;

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
            checkAndAssignFromDate(from);
            checkAndAssignToDate(to);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    public Event(String description, boolean isDone, String from, String to) throws IncorrectDateException {
        super(description, isDone);
        checkAndAssignFromDate(from);
        checkAndAssignToDate(to);
    }

    public Event(String description, String from, String to, HashSet<String> tags) throws IncorrectDateException {
        super(description, tags);
        checkAndAssignFromDate(from);
        checkAndAssignToDate(to);
    }

    public Event(String description, boolean isDone, String from, String to, HashSet<String> tags) throws IncorrectDateException {
        super(description, isDone, tags);
        checkAndAssignFromDate(from);
        checkAndAssignToDate(to);
    }

    private void checkAndAssignFromDate(String from) throws IncorrectDateException {
        try {
            this.from = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    private void checkAndAssignToDate(String to) throws IncorrectDateException {
        try {
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing an event task to be saved into the file
     */
    @Override
    public String interpretTaskToText() {
        String output = "E | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.from + " | " + this.to;
        if (this.tags == null) {
            return output;
        }

        output += " |t";
        for (String tag : this.tags) {
            if (!tag.isBlank()) {
                output += " #" + tag;
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        if (this.tags == null) {
            return output;
        }

        for (String tag : this.tags) {
            if (!tag.isBlank()) {
                output += " #" + tag;
            }
        }
        return output;
    }
}

package berry.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

import berry.exception.IncorrectDateException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    // The date to do the task by
    private LocalDate by;

    public Deadline(String description, String by) throws IncorrectDateException {
        super(description);
        checkAndAssignDate(by);
    }

    public Deadline(String description, boolean isDone, String by) throws IncorrectDateException {
        super(description);
        this.isDone = isDone;
        checkAndAssignDate(by);
    }

    public Deadline(String description, String by, HashSet<String> tags) throws IncorrectDateException {
        super(description, tags);
        checkAndAssignDate(by);
    }

    public Deadline(String description, boolean isDone, String by, HashSet<String> tags) throws IncorrectDateException {
        super(description, isDone, tags);
        this.isDone = isDone;
        checkAndAssignDate(by);
    }

    private void checkAndAssignDate(String by) throws IncorrectDateException {
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representing a deadline task to be saved into the file
     */
    @Override
    public String interpretTaskToText() {
        String output = "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.by;
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
        String output = "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

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

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDeadlineDateException;

/**
 * Represents deadlines
 */
public class Deadline extends Task {
    private LocalDate day;

    /**
     * Initializes a new Deadline object
     * @param type of task
     * @param detail of Deadline
     * @param marked whether deadline is marked or not
     * @param day of deadline
     */
    public Deadline(String type, String detail, boolean marked, LocalDate day) {
        super(type, detail, marked);
        this.day = day;
    }

    /**
     * Initialize a new Deadline object
     * @param type of task
     * @param detail of deadline
     * @param day of deadline
     */
    public Deadline(String type, String detail, String day) throws InvalidDeadlineDateException {
        super(type, detail);
        try {
            this.day = LocalDate.parse(day);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineDateException();
        }
    }

    /**
     * Returns deadline printed out properly.
     *
     * @return deadline in full details.
     */
    @Override
    public String toString() {
        if (marked) {
            return "[D][X] " + super.detail + " (by: " + this.day.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D][ ] " + super.detail + " (by: " + this.day.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy")) + ")";
        }
    }
}

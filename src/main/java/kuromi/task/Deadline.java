package kuromi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kuromi.exception.KuromiException;

/**
 * Deadline task represented by description and deadline. Extends from Task class.
 */
public class Deadline extends Task {
    /** The deadline date represented as a String **/
    protected String by;
    /** The deadline date represented as a LocalDate **/
    protected LocalDateTime date;

    /**
     * Main constructor (for invocation by most classes)
     *
     * @param description Description of a deadline.
     * @param by Deadline date of a deadline.
     */
    public Deadline(String description, String by) throws KuromiException {
        super(description);
        this.by = by;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.date = LocalDateTime.parse(by, format);
        } catch (DateTimeParseException e) {
            throw new KuromiException("OOPS!!! The format of the date must be in yyyy-MM-dd HH:mm");
        }
        if (this.date.isBefore(LocalDateTime.now())) {
            throw new KuromiException("OOPS!!! The date should be greater than the current date");
        }
    }

    /**
     * Secondary constructor (for invocation by Storage to put task in data into TaskList.
     *
     * @param description Description of a deadline.
     * @param by Deadline date of a deadline.
     * @param isDone Status of a deadline.
     */
    public Deadline(String description, String by, boolean isDone) throws KuromiException {
        super(description, isDone);
        this.by = by;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = LocalDateTime.parse(by, format);
    }

    /**
     * Get the detailed description of a deadline.
     * To store the current data into the file.
     *
     * @return Detailed description as a String.
     */
    @Override
    public String getDetailedDescription() {
        return super.description + " | " + this.by;
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public LocalDateTime getEndDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

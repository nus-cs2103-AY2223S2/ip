package kuromi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task represented by description and deadline. Extends from Task class.
 */
public class Deadline extends Task {
    /** The deadline date represented as a String **/
    protected String by;
    /** The deadline date represented as a LocalDate **/
    protected LocalDate date;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor (for invocation by most classes)
     *
     * @param description Description of a deadline.
     * @param by Deadline date of a deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        LocalDate date = LocalDate.parse(by);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = date.format(format);
        this.by = formattedDate;
    }

    /**
     * Secondary constructor (for invocation by Storage to put task in data into TaskList.
     *
     * @param description Description of a deadline.
     * @param by Deadline date of a deadline.
     * @param isDone Status of a deadline.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(by, format);
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
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

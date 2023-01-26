import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task
 */
public class Deadline extends Task {
    protected final String DATE_TIME_TO_PARSE = "yyyy-MM-dd HH:mm";
    protected final String DATE_TIME_TO_PRINT = "d MMM yyyy HH:mm";
    protected String by;
    protected LocalDateTime byDateTime;

    /**
     * Creates a Deadline task object
     * @param description Describes the task
     * @param by Represents when the task is due
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_TO_PARSE);
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TIME_TO_PRINT);
        this.byDateTime = LocalDateTime.parse(by, formatterParse);
        this.by = this.byDateTime.format(formatterPrint);
    }

    public LocalDateTime getDateTimeBy() {
        return this.byDateTime;
    }

    public String getFormattedBy() {
        return this.by;
    }

    /**
     * String representation of deadline task
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }
}

package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class that extends Task.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description The Event description.
     * @param date The date of the Deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDateTime output = LocalDateTime.parse(date, dateTimeFormatter);
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            this.by = output.format(newPattern);
        } catch (DateTimeParseException e) {
            LocalDate output = LocalDate.parse(date, dateFormatter);
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
            this.by = output.format(newPattern);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

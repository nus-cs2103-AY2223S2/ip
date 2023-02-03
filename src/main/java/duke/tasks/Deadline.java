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
        DateTimeFormatter newDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        DateTimeFormatter newDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDateTime output = LocalDateTime.parse(date, dateTimeFormatter);
            this.by = output.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate output = LocalDate.parse(date, dateFormatter);
            this.by = output.format(newDate);
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

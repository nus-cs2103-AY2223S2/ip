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
        LocalDateTime byDateTime;

        try {
            byDateTime = LocalDateTime.parse(date, dateTimeFormatter);
            this.by = byDateTime.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate byDate = LocalDate.parse(date, dateFormatter);
            this.by = byDate.format(newDate);
            byDateTime = byDate.atTime(23, 59);
        }
        assert byDateTime.isAfter(LocalDateTime.now()) : "Date has passed";

    }

    /**
     * Check if the deadline's date contains the given keyword.
     *
     * @param keyword The keyword argument.
     * @return A boolean value.
     */
    public boolean dateContains(String keyword) {
        if (this.by.length() >= keyword.length()) {
            return this.by.toLowerCase().contains(keyword.toLowerCase());
        } else {
            return false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n (by: " + this.by + ")";
    }
}

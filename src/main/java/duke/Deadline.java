package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * Type of task with description and a date to finish it by
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        this.by = LocalDate.parse(by, df);
    }

    /**
     * Convert to the formatted String to be saved in
     */
    @Override
    public String toSavedString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "D | " + super.toSavedString() + " | " + this.by.format(df);
    }

    /**
     * Convert to the formatted String to be displayed in
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(df) + ")";
    }
}

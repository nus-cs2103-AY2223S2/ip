package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        this.by = LocalDate.parse(by, df);
    }

    @Override
    public String toSavedString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "D | " + super.toSavedString() + " | " + this.by.format(df);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(df) + ")";
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createDeadline(String description) throws DateTimeParseException {
        String[] split = description.split("/by ");
        LocalDate d1 = LocalDate.parse(split[1]);
        return new Deadline(split[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + description + "(by: " + by + ")";
    }
}

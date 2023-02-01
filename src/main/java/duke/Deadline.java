package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter formatterToString = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        System.out.println(by);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public String getBy() {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatterToString) + ")";
    }
}

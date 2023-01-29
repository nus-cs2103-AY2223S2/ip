package donkeychat;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        this(description, false, by);

    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct format for dates, i.e. '12-10-2023 16:00'");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRINT_FORMAT) + ")";
    }

    @Override
    public String serialize() {
        return "D | " + super.serialize() + " | " + by.format(PRINT_FORMAT);
    }
}
package donkeychat;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A task that contains a complete-by date.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a new Deadline task.
     * @param description Description of the task.
     * @param by Complete-by date for the task.
     */
    public Deadline(String description, String by) {
        this(description, false, by);

    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, FORMAT_DATE_TIME);
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct format for dates, i.e. '12-10-2023 16:00'");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMAT_PRINT) + ")";
    }

    @Override
    public String serialize() {
        return "D | " + super.serialize() + " | " + by.format(FORMAT_PRINT);
    }
}
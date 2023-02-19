package james.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The deadline class.
 * A deadline is a task that is listed on the taskList with a deadline of /by.
 */
public class Deadline extends Task {
    /** Time in d/MM/yyyy HHmm format */
    protected LocalDateTime by;
    /**
     * Constructs a Deadline Object.
     * @param description The task description.
     * @param by The deadline of the task.
     * @throws DateTimeParseException If the deadline is not in the correct format.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.by = LocalDateTime.parse(by, format);

    }

    @Override
    public String toString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + "(by:" + dateFormat + ")";
    }
    @Override
    public String toStoreString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        return "D | " + super.toStoreString() + " | " + dateFormat;
    }

}




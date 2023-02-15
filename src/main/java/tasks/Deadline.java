package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task that has to be done by a certain time.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;

    /**
     * Constructs Deadline instance.
     * @param taskName Name of task.
     * @param by Deadline of task.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException err) {
            this.byDateTime = null;
        }
    }

    public String getByTime() {
        if (this.byDateTime != null) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        } else {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatus() + "] " + super.toString().strip()
                + " (by: " + this.getByTime() + ")";
    }

    @Override
    public String toSaveString() {
        return "D" + "=" + super.getStatus() + "=" + super.toSaveString().strip()
                + "=" + this.by;
    }
}

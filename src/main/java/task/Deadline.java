package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends from the Task class.
 * It has an additional deadline attribute.
 */
public class Deadline extends Task {
    protected LocalDateTime ddl;
    protected String name;

    /**
     * Constructor
     *
     * @param name The content of the deadline command.
     */
    public Deadline(String name) {
        super(name);
        this.name = name.substring("deadline".length() + 1, name.indexOf("/by") - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.ddl = LocalDateTime.parse(name.substring(name.indexOf("/by") + 4), formatter);
    }

    public LocalDateTime getDeadline() {
        return ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, "[ ] ".length())
                + this.name + " (by: " + ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}

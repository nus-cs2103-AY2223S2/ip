package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends from the Task class.
 * It has an additional deadline attribute.
 */
public class Deadline extends Task {
    protected LocalDate ddl;
    protected String name;

    /**
     * Constructor
     *
     * @param name The content of the deadline command.
     */
    public Deadline(String name) {
        super(name);
        this.name = name.substring("deadline".length() + 1, name.indexOf("/by") - 1);
        this.ddl = LocalDate.parse(name.substring(name.indexOf("/by") + 4));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(0, "[ ] ".length())
                + this.name + " (by: " + ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }


}

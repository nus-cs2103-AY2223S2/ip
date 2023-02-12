package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the Deadline class which is a sub-class of the Task class.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Initialises the by field in LocalDate type.
     * @param description
     * @param by
     * @throws DateTimeException
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            throw new DateTimeException("Make sure the deadline in yyyy-mm-dd");
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getTimeline() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

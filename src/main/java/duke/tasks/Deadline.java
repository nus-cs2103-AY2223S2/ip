package duke.tasks;

import java.time.format.DateTimeParseException;

import duke.parser.DateTimeParser;

/**
 * Deadline class extends the Task class.
 * @author Leng Wei Cong, Justin
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor.
     * @param description the description of the deadline task
     * @param by          the end datetime of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the end datetime.
     * @return the end datetime.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    /**
     * Updates deadline of deadline task
     * @param by
     * @return Task the instance
     */
    public Task updateBy(String by) {
        try {
            this.by = DateTimeParser.parse(by);
        } catch (DateTimeParseException e) {
            DateTimeParser.showDateTimeParseErrorMessage();
        }

        return this;
    }
}

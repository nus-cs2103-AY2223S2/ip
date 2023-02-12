package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for deadline task.
     * @param description Description for deadline task.
     * @param by Date of deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by " + by.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the task in the save file.
     * @return Representation of the task in the save file.
     */
    @Override
    public String toSavedString() {
        return "D" + "|" + (super.isDone ? "1" : "0")
                + "|" + super.description + "|" + this.by;
    }

    @Override
    public void setComponent(TaskComponent component, Object detail) throws DukeException {
        switch (component) {
        case DESCRIPTION:
            this.description = (String) detail;
            break;
        case BY:
            this.by = (LocalDate) detail;
            break;
        default:
            throw new DukeException("DEADLINE task does not have this component: " + component);
        }
    }
}

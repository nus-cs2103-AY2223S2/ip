package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The DatedTask represents a Task that contains a date.
 */
public class DatedTask extends Task {
    // Unique identifier for Serializer implementation: do not change var name
    private static final long serialVersionUID = 110;

    private LocalDate imptDate;

    /**
     * Constructs a Dated Task.
     *
     * @param name    The name of the task.
     * @param impDate The date to be used for comparisons.
     * @throws DukeException If imptDate is not a valid date.
     */
    protected DatedTask(String name, String impDate) throws DukeException {
        super(name);
        try {
            this.imptDate = LocalDate.parse(impDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Write the date in this format: YYYY-MM-DD");
        }
    }

    /**
     * @return True, the class is dated.
     */
    @Override
    public boolean hasDate() {
        return true;
    }

    @Override
    public int compareTo(Task other) {
        // done tasks are equally unimportant
        if (other.isMarkedDone() && this.isMarkedDone()) {
            return 0;
        }

        if (!other.isMarkedDone() && !this.isMarkedDone()) {
            if (other instanceof DatedTask) {
                DatedTask otherDated = (DatedTask) other;
                return imptDate.compareTo(otherDated.imptDate);
            }

            // we are more important
            return -1;
        }

        return this.isMarkedDone() ? 1 : -1;
    }
}

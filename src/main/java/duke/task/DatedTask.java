package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The DatedTask represents a Task that contains a date.
 */
public class DatedTask extends Task {
    private LocalDate imptDate;

    /**
     * Constructor for DatedTask
     *
     * @param name
     * @param impDate
     */
    protected DatedTask(String name, String impDate) throws DukeException {
        super(name);
        try {
            this.imptDate = LocalDate.parse(impDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Write the date in this format: YYYY-MM-DD");
        }

    }

    @Override
    public boolean hasDate() {
        return true;
    }

    @Override
    public int compareTo(Task t) {
        if (t.isMarkedDone() && this.isMarkedDone()) {
            return 0;
        } else if (!t.isMarkedDone() && !this.isMarkedDone()) {
            if (!t.hasDate()) {
                return -1;
            } else {
                if (t instanceof DatedTask) {
                    DatedTask other = (DatedTask) t;
                    return imptDate.compareTo(other.imptDate);
                }

                // we are more important
                return -1;
            }
        } else {
            return this.isMarkedDone() ? 1 : -1;
        }
    }
}

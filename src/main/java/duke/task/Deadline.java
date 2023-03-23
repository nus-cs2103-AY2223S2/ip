package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import duke.DukeException;

/**
 * Class representing a Deadline.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy[ HHmm]");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma");
    protected String byStr;
    protected LocalDateTime byDateTime;

    /**
     * Returns a Deadline object.
     *
     * @param description Description of the task.
     * @param byStr       The 'by' date.
     * @throws DukeException If description or byStr are empty.
     */
    public Deadline(String description, String byStr) throws DukeException {
        super(description.trim(), TaskIcon.DEADLINE);
        setByDate(byStr);
    }

    /**
     * Returns a Deadline object. Accepts additional parameter tag.
     * @param description Description of the task.
     * @param byStr The 'by' date
     * @param tags Tags of the Deadline.
     * @throws DukeException
     */
    public Deadline(String description, String byStr, String tags) throws DukeException {
        super(description, TaskIcon.DEADLINE, tags);
        setByDate(byStr);
    }

    private void setByDate(String byStr) throws DukeException {
        this.byStr = byStr.trim();
        if (this.byStr.equals("")) {
            throw new DukeException("The 'by' date of a deadline cannot be empty.");
        }
        try {
            TemporalAccessor temporalAccessor = INPUT_FORMATTER.parseBest(
                    byStr, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                byDateTime = (LocalDateTime) temporalAccessor;
            } else {
                byDateTime = ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            byDateTime = null;
        }
    }

    public String getByDate() {
        return byStr;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (by: %s)",
                super.toString(),
                byDateTime == null ? byStr : byDateTime.format(OUTPUT_FORMATTER)
        );
    }
}

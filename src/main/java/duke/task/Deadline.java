package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime cutoff;

    /**
     * Creates a Deadline object.
     *
     * @param isDone Is the deadline over.
     * @param description Description of the deadline.
     * @param cutoff Cutoff of the deadline.
     */
    public Deadline(boolean isDone, String description, LocalDateTime cutoff) {
        super(isDone, description);

        assert cutoff != null;

        this.cutoff = cutoff;
    }

    /**
     * Returns a Deadline object created using the specified data that was loaded from storage.
     *
     * @param args Data about the deadline that was loaded from storage.
     * @return The Deadline object created using the data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type or format in args.
     */
    public static Deadline createFromStorage(String[] args) throws DukeException {
        assert args != null;

        if (args.length != 4) {
            throw new DukeException("A deadline in storage has missing data!");
        }

        if (!BooleanUtils.isBooleanStr(args[1])) {
            throw new DukeException("A deadline in storage has an incorrect data type!");
        }

        args = Task.formatStrsFromStorage(args);

        LocalDateTime cutoff;
        try {
            cutoff = LocalDateTime.parse(args[3]);
        } catch (DateTimeParseException e) {
            throw new DukeException("A deadline in storage has an incorrectly formatted cutoff date and time!");
        }

        return new Deadline(Boolean.parseBoolean(args[1]), args[2], cutoff);
    }

    @Override
    public String getStorageStr() {
        return String.format("D | %s | %s", super.getStorageStr(), Task.formatStrForStorage(cutoff.toString()));
    }

    @Override
    public String toString() {
        String cutoffStr = cutoff.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);

        return String.format("[D]%s (by: %s)", super.toString(), cutoffStr);
    }

    @Override
    protected Task createCopy() {
        return new Deadline(isDone(), getDescription(), cutoff);
    }
}

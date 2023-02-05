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
    private static final char SYMBOL = 'D';

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

        validateNoMissingData(args);

        String[] formattedArgs = Task.formatStrsFromStorage(args);

        boolean isDone = extractValidIsDone(formattedArgs);
        LocalDateTime cutoff = extractValidCutoff(formattedArgs);

        return new Deadline(isDone, formattedArgs[2], cutoff);
    }

    @Override
    public String getStorageStr() {
        return String.format("%c %c %s %c %s", SYMBOL, FIELD_DIVIDER, super.getStorageStr(), FIELD_DIVIDER,
                Task.formatStrForStorage(cutoff.toString()));
    }

    @Override
    public String toString() {
        String cutoffStr = cutoff.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);

        return String.format("[%c]%s (by: %s)", SYMBOL, super.toString(), cutoffStr);
    }

    @Override
    protected Task createCopy() {
        return new Deadline(isDone(), getDescription(), cutoff);
    }

    private static void validateNoMissingData(String[] args) throws DukeException {
        if (args.length != 4) {
            throw new DukeException("A deadline in storage has missing data!");
        }
    }

    private static boolean extractValidIsDone(String[] formattedArgs) throws DukeException {
        if (!BooleanUtils.isBooleanStr(formattedArgs[1])) {
            throw new DukeException("A deadline in storage has an incorrect data type!");
        }

        return Boolean.parseBoolean(formattedArgs[1]);
    }

    private static LocalDateTime extractValidCutoff(String[] formattedArgs) throws DukeException {
        try {
            return LocalDateTime.parse(formattedArgs[3]);
        } catch (DateTimeParseException e) {
            throw new DukeException("A deadline in storage has an incorrectly formatted cutoff date and time!");
        }
    }
}

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

    private static final int EXPECTED_ARG_COUNT = 4;

    private static final int DONE_ARG_INDEX = 1;
    private static final int DESCRIPTION_ARG_INDEX = 2;
    private static final int CUTOFF_ARG_INDEX = 3;

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
        validateNoMissingData(args);

        String[] formattedArgs = Task.formatStrsFromStorage(args);

        boolean isDone = extractValidIsDone(formattedArgs);
        LocalDateTime cutoff = extractValidCutoff(formattedArgs);

        return new Deadline(isDone, formattedArgs[DESCRIPTION_ARG_INDEX], cutoff);
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
        assert args != null;

        if (args.length != EXPECTED_ARG_COUNT) {
            throw new DukeException("A deadline in storage has missing data!");
        }
    }

    private static boolean extractValidIsDone(String[] formattedArgs) throws DukeException {
        assert formattedArgs != null;
        assert formattedArgs.length >= DONE_ARG_INDEX + 1;

        if (!BooleanUtils.isBooleanStr(formattedArgs[DONE_ARG_INDEX])) {
            throw new DukeException("A deadline in storage has an incorrect data type!");
        }

        return Boolean.parseBoolean(formattedArgs[DONE_ARG_INDEX]);
    }

    private static LocalDateTime extractValidCutoff(String[] formattedArgs) throws DukeException {
        assert formattedArgs != null;
        assert formattedArgs.length >= CUTOFF_ARG_INDEX + 1;

        try {
            return LocalDateTime.parse(formattedArgs[CUTOFF_ARG_INDEX]);
        } catch (DateTimeParseException e) {
            throw new DukeException("A deadline in storage has an incorrectly formatted cutoff date and time!");
        }
    }
}

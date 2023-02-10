package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    private static final char SYMBOL = 'T';

    private static final int EXPECTED_ARG_COUNT = 3;

    private static final int DONE_ARG_INDEX = 1;
    private static final int DESCRIPTION_ARG_INDEX = 2;

    /**
     * Creates a ToDo object.
     *
     * @param isDone Is the to-do done.
     * @param description Description of the to-do.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a ToDo object created using the specified data that was loaded from storage.
     *
     * @param args Data about the to-do that was loaded from storage.
     * @return The ToDo object created using the data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type in args.
     */
    public static ToDo createFromStorage(String[] args) throws DukeException {
        validateNoMissingData(args);

        String[] formattedArgs = Task.formatStrsFromStorage(args);

        boolean isDone = extractValidIsDone(formattedArgs);
        String description = formattedArgs[DESCRIPTION_ARG_INDEX];

        return new ToDo(isDone, description);
    }

    @Override
    public String getStorageStr() {
        return String.format("%c %c %s", SYMBOL, FIELD_DIVIDER, super.getStorageStr());
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", SYMBOL, super.toString());
    }

    @Override
    protected Task createCopy() {
        return new ToDo(isDone(), getDescription());
    }

    private static void validateNoMissingData(String[] args) throws DukeException {
        assert args != null;

        if (args.length != EXPECTED_ARG_COUNT) {
            throw new DukeException("A to-do in storage has missing data!");
        }
    }

    private static boolean extractValidIsDone(String[] formattedArgs) throws DukeException {
        assert formattedArgs != null;
        assert formattedArgs.length >= DONE_ARG_INDEX + 1;

        if (!BooleanUtils.isBooleanStr(formattedArgs[DONE_ARG_INDEX])) {
            throw new DukeException("A to-do in storage has an incorrect data type!");
        }

        return Boolean.parseBoolean(formattedArgs[1]);
    }
}

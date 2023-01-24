package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;

/**
 * Represents a To-Do task.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDoTask object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a ToDo object created using the specified data that was loaded from storage.
     *
     * @param args Data about the ToDo that was loaded from storage.
     * @return The ToDo object created using data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type in args.
     */
    public static ToDo createFromStorage(String[] args) throws DukeException {
        if (args.length != 3) {
            throw new DukeException("Failed to load a to-do from storage due to missing data.");
        }

        if (!BooleanUtils.isBooleanString(args[1])) {
            throw new DukeException("Failed to load a to-do from storage due to incorrect data type.");
        }

        args = Task.formatStringsFromStorage(args);

        return new ToDo(Boolean.parseBoolean(args[1]), args[2]);
    }

    @Override
    public String getStorageString() {
        return String.format("T | %s", super.getStorageString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    protected Task createCopy() {
        return new ToDo(isDone(), getDescription());
    }
}

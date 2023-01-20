package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Creates an EventTask object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     * @param start When the event starts.
     * @param end When the event ends.
     */
    public Event(boolean isDone, String description, String start, String end) {
        super(isDone, description);

        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event object created using the specified data that was loaded from storage.
     *
     * @param args Data about the event that was loaded from storage.
     * @return The Event object created using data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type in args.
     */
    public static Event createFromStorage(String[] args) throws DukeException {
        if (args.length != 5) {
            throw new DukeException("Failed to load an event from storage due to missing data.");
        }

        if (!BooleanUtils.isBooleanString(args[1])) {
            throw new DukeException("Failed to load an event from storage due to incorrect data type.");
        }

        args = Task.formatStringsFromStorage(args);

        return new Event(Boolean.parseBoolean(args[1]), args[2], args[3], args[4]);
    }

    @Override
    public String getStorageString() {
        String startStr = Task.formatStringForStorage(start);
        String endStr = Task.formatStringForStorage(end);

        return String.format("E | %s | %s | %s", super.getStorageString(), startStr, endStr);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }
}

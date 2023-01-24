package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates an Event object.
     *
     * @param isDone Is the event done.
     * @param description Description of the event.
     * @param start When the event starts.
     * @param end When the event ends.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);

        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event object created using the specified data that was loaded from storage.
     *
     * @param args Data about the event that was loaded from storage.
     * @return The Event object created using data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type or format in args.
     */
    public static Event createFromStorage(String[] args) throws DukeException {
        if (args.length != 5) {
            throw new DukeException("Failed to load an event from storage due to missing data.");
        }

        if (!BooleanUtils.isBooleanString(args[1])) {
            throw new DukeException("Failed to load an event from storage due to incorrect data type.");
        }

        args = Task.formatStringsFromStorage(args);

        LocalDateTime start;
        try {
            start = LocalDateTime.parse(args[3]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to load an event from storage due to failure to parse start of event.");
        }

        LocalDateTime end;
        try {
            end = LocalDateTime.parse(args[4]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to load an event from storage due to failure to parse end of event.");
        }

        return new Event(Boolean.parseBoolean(args[1]), args[2], start, end);
    }

    @Override
    public String getStorageString() {
        String startStr = Task.formatStringForStorage(start.toString());
        String endStr = Task.formatStringForStorage(end.toString());

        return String.format("E | %s | %s | %s", super.getStorageString(), startStr, endStr);
    }

    @Override
    public String toString() {
        String startStr = start.format(LocalDateTimeUtils.outputDateTimeFormatter);
        String endStr = end.format(LocalDateTimeUtils.outputDateTimeFormatter);

        return String.format("[E]%s (from: %s to: %s)", super.toString(), startStr, endStr);
    }

    @Override
    protected Task createCopy() {
        return new Event(isDone(), getDescription(), start, end);
    }
}

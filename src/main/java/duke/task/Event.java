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

        assert start != null;
        assert end != null;

        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event object created using the specified data that was loaded from storage.
     *
     * @param args Data about the event that was loaded from storage.
     * @return The Event object created using the data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type or format in args.
     */
    public static Event createFromStorage(String[] args) throws DukeException {
        assert args != null;

        if (args.length != 5) {
            throw new DukeException("An event in storage has missing data!");
        }

        if (!BooleanUtils.isBooleanStr(args[1])) {
            throw new DukeException("An event in storage has an incorrect data type!");
        }

        args = Task.formatStrsFromStorage(args);

        LocalDateTime start;
        try {
            start = LocalDateTime.parse(args[3]);
        } catch (DateTimeParseException e) {
            throw new DukeException("An event in storage has an incorrectly formatted start of event!");
        }

        LocalDateTime end;
        try {
            end = LocalDateTime.parse(args[4]);
        } catch (DateTimeParseException e) {
            throw new DukeException("An event in storage has an incorrectly formatted end of event!");
        }

        return new Event(Boolean.parseBoolean(args[1]), args[2], start, end);
    }

    @Override
    public String getStorageStr() {
        String startStr = Task.formatStrForStorage(start.toString());
        String endStr = Task.formatStrForStorage(end.toString());

        return String.format("E | %s | %s | %s", super.getStorageStr(), startStr, endStr);
    }

    @Override
    public String toString() {
        String startStr = start.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);
        String endStr = end.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);

        return String.format("[E]%s (from: %s to: %s)", super.toString(), startStr, endStr);
    }

    @Override
    protected Task createCopy() {
        return new Event(isDone(), getDescription(), start, end);
    }
}

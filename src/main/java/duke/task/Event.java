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
    private static final char SYMBOL = 'E';

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
     * @return The Event object created using the data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type or format in args.
     */
    public static Event createFromStorage(String[] args) throws DukeException {
        if (args.length != 5) {
            throw new DukeException("An event in storage has missing data!");
        }

        if (!BooleanUtils.isBooleanStr(args[1])) {
            throw new DukeException("An event in storage has an incorrect data type!");
        }

        String[] formattedArgs = Task.formatStrsFromStorage(args);

        LocalDateTime start;
        try {
            start = LocalDateTime.parse(formattedArgs[3]);
        } catch (DateTimeParseException e) {
            throw new DukeException("An event in storage has an incorrectly formatted start of event!");
        }

        LocalDateTime end;
        try {
            end = LocalDateTime.parse(formattedArgs[4]);
        } catch (DateTimeParseException e) {
            throw new DukeException("An event in storage has an incorrectly formatted end of event!");
        }

        boolean isDone = Boolean.parseBoolean(formattedArgs[1]);
        String description = formattedArgs[2];

        return new Event(isDone, description, start, end);
    }

    @Override
    public String getStorageStr() {
        String startStr = Task.formatStrForStorage(start.toString());
        String endStr = Task.formatStrForStorage(end.toString());

        return String.format("%c %c %s %c %s %c %s", SYMBOL, FIELD_DIVIDER, super.getStorageStr(), FIELD_DIVIDER,
                startStr, FIELD_DIVIDER, endStr);
    }

    @Override
    public String toString() {
        String startStr = start.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);
        String endStr = end.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);

        return String.format("[%c]%s (from: %s to: %s)", SYMBOL, super.toString(), startStr, endStr);
    }

    @Override
    protected Task createCopy() {
        return new Event(isDone(), getDescription(), start, end);
    }
}

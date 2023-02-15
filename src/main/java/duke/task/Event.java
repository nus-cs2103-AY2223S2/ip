package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * The Event class represents a task that has a start date and end date.
 */
public class Event extends Deadline {
    protected LocalDateTime startDate;

    Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, endDate);
        this.startDate = startDate;
    }

    Event(String description, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        super(description, endDate, isDone);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("Event %s (from %s to %s)", getTaskDescription(),
                startDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)),
                endDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)));
    }

    /**
     * Provides a serialized format for the Event object.
     *
     * @return serialized format for the Event object
     */
    @Override
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "E", isDone ? "1" : "0", description,
                startDate.toString(), endDate.toString());
    }

    /**
     * Deserializes a serialized Event object.
     *
     * @param data string of serialized Event object to deserialize
     * @return deserialized Event object
     * @throws DukeException
     */
    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Event(split[2], LocalDateTime.parse(split[3]), LocalDateTime.parse(split[4]), split[1].equals("1"));
    }
}

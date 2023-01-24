package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        return String.format("Event / %s (from %s to %s)", getTaskDescription(),
                startDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)),
                endDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)));
    }

    @Override
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "E", isDone ? "1" : "0", description,
                startDate.toString(), endDate.toString());
    }

    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Event(split[2], LocalDateTime.parse(split[3]), LocalDateTime.parse(split[4]), split[1].equals("1"));
    }
}

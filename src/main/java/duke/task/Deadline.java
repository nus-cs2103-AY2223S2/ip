package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected static final String DATETIME_DISPLAY_PATTERN = "MMM d yyyy hh:mm";
    protected LocalDateTime endDate;

    Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    Deadline(String description, LocalDateTime endDate, boolean isDone) {
        super(description, isDone);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("Deadline / %s (by %s)", getTaskDescription(),
                endDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)));
    }

    @Override
    public String serialize() {
        // TODO: Handle case where description contains "|"
        return String.join("|", "D", isDone ? "1" : "0", description, endDate.toString());
    }

    public static Task deserialize(String data) throws DukeException {
        String[] split = splitDataStr(data);
        // TODO: Verify task data (similar to processing queries)
        return new Deadline(split[2], LocalDateTime.parse(split[3]), split[1].equals("1"));
    }
}

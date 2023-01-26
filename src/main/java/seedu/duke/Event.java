package seedu.duke;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        super.type = 'E';
    }

    @Override
    public String taskInFileFormat() {
        return super.taskInFileFormat() + " | " + start + " to " + end;
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString() + " (from: " + Ui.getDateTimeOutput(start) + " to: "
                + Ui.getDateTimeOutput(end) + ")";

    }
}
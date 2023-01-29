package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone) ? "X" : " ";

        return "E" + "~" + mark + "~" + this.description
                + "~" + this.from + "~" + this.to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: " + Task.getDateTimeString(this.from)
                + " ; to: " + Task.getDateTimeString(this.to) + ")";
    }
}

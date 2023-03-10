package duke.task;

import java.time.LocalDateTime;

import duke.ui.Ui;

/**
 * Encapsulates a task with both start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task.
     *
     * @param description A sentence that describes the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
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

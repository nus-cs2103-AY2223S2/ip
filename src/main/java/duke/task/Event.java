package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with an associated start and end time
 */
@TaskInfo(type = "E")
public class Event extends Task {
    /**
     * String representation of the start time
     */
    private final LocalDateTime start;

    /**
     * String representation of the end time
     */
    private final LocalDateTime end;

    public Event(
        String task,
        int priority,
        LocalDateTime start,
        LocalDateTime end
    ) {
        super(task, priority);
        this.start = start;
        this.end = end;
    }

    public Event(
        String task,
        LocalDateTime start,
        LocalDateTime end
    ) {
        this(task, DEFAULT_PRIORITY, start, end);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
            super.toString(), 
            start.format(DateTimeFormatter.ofPattern("dd/MM kk:hh")),
            end.format(DateTimeFormatter.ofPattern("dd/MM kk:hh"))
        );
    }
}

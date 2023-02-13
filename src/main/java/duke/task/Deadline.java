package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with an associated deadline
 */
@TaskInfo(type = "D")
public class Deadline extends Task {
    /**
     * String representation of the deadline
     */
    private final LocalDateTime deadline;

    public Deadline(String task, int priority, LocalDateTime time) {
        super(task, priority);
        this.deadline = time;
    }

    public Deadline(String task, LocalDateTime time) {
        this(task, DEFAULT_PRIORITY, time);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", 
            super.toString(), 
            deadline.format(DateTimeFormatter.ofPattern("dd/MM kk:hh"))
        );
    }
}

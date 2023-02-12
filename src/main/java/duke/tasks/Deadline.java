package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline task.
 */
public class Deadline extends Task {
    /** The deadline of this task. **/
    private LocalDateTime deadline;

    /**
     * Constructor for a Deadline task.
     * @param name Name of the task
     * @param deadline Deadline of the task
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String toSaveFormat() {
        return String.format("D,%s,%s,%s", this.name, this.getStatusIcon(), this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}

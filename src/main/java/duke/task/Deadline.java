package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task: Deadline
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Initializes a deadline task
     * @param description Description of a deadline task
     * @param deadline deadline of task
     * @param isDone whether task is completed
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:"
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String parse() {
        return "D" + " | " + super.parse() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

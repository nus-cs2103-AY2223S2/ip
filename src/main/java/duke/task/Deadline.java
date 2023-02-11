package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that has description of task and a deadline
 */
public class Deadline extends TaskWithDate {
    private LocalDateTime deadline;

    /**
     * Constructor for deadline.
     * @param desc
     * @param deadline
     */
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc, "D", deadline);
        this.deadline = deadline;
    }

    /**
     * Gets deadline of DeadlineTask.
     * @return deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Prints string representation of deadline task.
     * @return string representation of deadline task
     */
    public String toString() {
        return super.toString()
                + String.format("[by %s]", this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}

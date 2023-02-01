package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that has description of task and a deadline
 */
public class Deadline extends Task {
    LocalDate deadline;

    /**
     * Constructor for deadline.
     * @param desc
     * @param deadline
     */
    public Deadline(String desc,LocalDate deadline) {
        super(desc,"D");
        this.deadline = deadline;
    }

    /**
     * Gets deadline of DeadlineTask.
     * @return deadline
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Prints string representation of deadline task.
     * @return string representation of deadline task
     */
    public String toString() {
        return super.toString() + String.format("[by %s]", this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

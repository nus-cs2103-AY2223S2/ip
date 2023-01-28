package duke.task;

import java.time.LocalDateTime;

import duke.datetime.DateTime;

/**
 * Task to represent Deadline task created by user
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline
     *
     * @param name Name of task
     * @param deadline Task's deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * @return String version of Deadline
     */
    @Override
    public String toString() {
        String deadline = DateTime.getDateTimeString(this.deadline);
        if (super.getIsCompleted()) {
            return String.format("[D][X] %s (by: %s)\n", super.getTaskName(), deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)\n", super.getTaskName(), deadline);
        }
    }
}

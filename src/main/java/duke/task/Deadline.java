package duke.task;

import duke.datetime.DateTime;

import java.time.LocalDateTime;

/**
 * Task to represent Deadline task created by user
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

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
            return String.format("[D][ ] %s (by: %s)\n",super.getTaskName(), deadline);
        }
    }
}

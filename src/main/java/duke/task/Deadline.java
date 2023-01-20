package duke.task;

import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Creates a Deadline object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);

        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineStr = deadline.format(LocalDateTimeUtils.outputDateTimeFormatter);

        return String.format("[D]%s (by: %s)", super.toString(), deadlineStr);
    }
}

package duke.task;

import java.time.LocalDateTime;

import duke.ui.Ui;

/**
 * Encapsulates a task with deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description A sentence that describes the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        super.type = 'D';
    }

    @Override
    public String taskInFileFormat() {
        return super.taskInFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString() + " (by: " + Ui.getDateTimeOutput(by) + ")";
    }
}

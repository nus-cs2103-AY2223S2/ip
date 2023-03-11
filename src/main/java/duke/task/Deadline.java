package duke.task;

import java.time.LocalDateTime;

import duke.UI.UI;

/**
 * The Deadline of tasks.
 * Inherits from the superclass Task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * The constructor of Deadline task.
     * @param taskDescription The name of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
        super.typeOfTask = 'D';
    }

    @Override
    public String savedTaskFormat() {
        return super.savedTaskFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + super.typeOfTask + "]" + super.toString() + "(by: " + UI.getOutputDateFormat(by) + ")";
    }
}

package duke.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates a task which has a specific deadline denoted by /by.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class TaskDeadline extends Task {
    private LocalDateTime time;

    /**
     * Constructor for creating a TaskDeadline object.
     *
     * @param task     The task name to be completed.
     * @param time     The task's deadline.
     *
     */
    public TaskDeadline(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Returns string representation of a Deadline object which users can see in the command line.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (%s) ", time);
    }

    /**
     * Returns string representation of a TaskDeadline object that is stored in the duke.functions.Duke.txt file.
     *
     * @return String representation of TaskDeadline.
     */
    @Override
    public String toStringDb() {
        return String.format(
                "%s|%s|%s",
                "D",
                super.toStringDb(),
                this.time
        );
    }
}
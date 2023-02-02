/**
 * The class representing a TaskDeadline task.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */
package duke.tasks;

import java.time.LocalDateTime;

public class TaskDeadline extends Task {
    private LocalDateTime time;
    /**
     * Given a task and a deadline, constructs a TaskDeadline task.
     *
     * @param task     The task to be completed.
     * @param time     The task's deadline.
     *
     * @author JamesLiuZX
     * AY2223-S2 CS2103T
     */

    public TaskDeadline(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (%s) ", time);
    }

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
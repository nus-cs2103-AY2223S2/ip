package duke.tasks;

import java.time.LocalDateTime;

/**
 * Given a task and a time range, constructs a Task event.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T

 */
public class TaskEvent extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for creating a TaskEvent object.
     *
     * @param task     The task name to be completed.
     * @param from     The task's start date and time.
     * @param to       The task's end date and time.
     */
    public TaskEvent(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of a TaskEvent object which users can see in the command line.
     *
     * @return String representation of TaskEvent.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (%s%s)", this.from, this.to);
    }

    /**
     * Returns string representation of a TaskEvent object that is stored in the duke.functions.Duke.txt file.
     *
     * @return String representation of TaskEvent.
     */
    @Override
    public String toStringDb() {
        return String.format(
                "E|%s|%s|%s",
                super.toStringDb(),
                this.from, this.to
        );
    }
}
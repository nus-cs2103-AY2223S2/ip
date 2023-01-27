package cbot.task;

import java.time.LocalDateTime;

import cbot.time.TimeStuff;

/**
 * Task with a due date. Stores the description of the task, whether it has been completed or not,
 * and the eponymous deadline.
 *
 * @see Task
 * @see Event
 */
public class Deadline extends Task {
    public static final String DEADLINE_SYMBOL = "D";

    private final LocalDateTime due;

    /**
     * Constructs a task with the given description, completion status, and deadline.
     *
     * @param desc The task description.
     * @param isDone Whether the task is done.
     * @param due The deadline of the task.
     */
    public Deadline(String desc, boolean isDone, LocalDateTime due) {
        super(desc, isDone);
        this.due = due;
    }

    /**
     * Constructs a not-yet-done task with the given description and deadline.
     *
     * @param desc The task description.
     * @param due The deadline of the task.
     */
    public Deadline(String desc, LocalDateTime due) {
        super(desc);
        this.due = due;
    }

    @Override
    public String getSymbol() {
        return DEADLINE_SYMBOL;
    }

    @Override
    public boolean hasTime() {
        return true;
    }

    @Override
    public LocalDateTime getTime() {
        return this.due;
    }

    @Override
    public String toString() {
        return String.format("%s (< %s)",
                super.toString(), TimeStuff.text(this.due));
    }

    @Override
    public String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}

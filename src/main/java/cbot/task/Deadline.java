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
     * Constructs a task with the given description, deadline, and completion status.
     * The last variable is optional, and defaults to false (not done).
     *
     * @param desc The task description.
     * @param due The deadline of the task.
     * @param isDone (Optional) Whether the task is done.
     */
    public Deadline(String desc, LocalDateTime due, boolean ... isDone) {
        super(desc, isDone);
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
                super.toString(), TimeStuff.dtToText(this.due));
    }

    @Override
    public String makeFileFriendly() {
        return String.format("%s%s%s",
                super.makeFileFriendly(), SEP, this.due);
    }
}

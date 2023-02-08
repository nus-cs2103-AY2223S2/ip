package cbot.task;

import java.time.LocalDateTime;

import cbot.time.TimeStuff;

/**
 * Task with a start and end time. Stores the description of the task, whether it has been completed or not,
 * and the start and end.
 *
 * @see Task
 * @see Deadline
 */
public class Event extends Task {
    public static final String EVENT_SYMBOL = "E";

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    /**
     * Constructs a task with the given description, start datetime, end datetime, and completion status.
     * The last variable is optional, and defaults to false (not done).
     *
     * @param desc The task description.
     * @param isDone Whether the task is done.
     * @param fromTime The start datetime.
     * @param toTime The end datetime.
     */
    public Event(String desc, LocalDateTime fromTime, LocalDateTime toTime, boolean ... isDone) {
        super(desc, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getSymbol() {
        return EVENT_SYMBOL;
    }

    @Override
    public boolean hasTime() {
        return true;
    }

    @Override
    public LocalDateTime getTime() {
        return this.fromTime;
    }

    @Override
    public String toString() {
        return String.format("%s (%s - %s)",
                super.toString(), TimeStuff.dtToText(this.fromTime), TimeStuff.dtToText(this.toTime));
    }

    @Override
    public String makeFileFriendly() {
        return String.format("%s%s%s%s%s",
                super.makeFileFriendly(), SEP, this.fromTime, SEP, this.toTime);
    }
}

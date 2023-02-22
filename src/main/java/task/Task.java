package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a textual objective, completion status,
 * and some chronological order.
 */
public abstract class Task {
    /** <code>DateTimeFormatter</code> format for parsing supplied time <code>String</code>s. */
    public static final String DATE_IN_FMT_STR = "yyyy-MM-dd HH:mm";
    /** <code>DateTimeFormatter</code> format for outputting time <code>String</code>s. */
    public static final String DATE_OUT_FMT_STR = "dd LLL yyyy hh:mm a";
    /** <code>DateTimeFormatter</code> for parsing supplied time <code>String</code>s. */
    protected static final DateTimeFormatter DATE_IN_FMT = DateTimeFormatter.ofPattern(DATE_IN_FMT_STR);
    /** <code>DateTimeFormatter</code> for outputting time <code>String</code>s. */
    protected static final DateTimeFormatter DATE_OUT_FMT = DateTimeFormatter.ofPattern(DATE_OUT_FMT_STR);

    /** The textual objective of this task. */
    protected String objective;
    /** The completion status of this task. */
    protected boolean isDone;

    /**
     * Creates a task with the specified textual objective.
     *
     * @param objective description of this task's objective.
     */
    public Task(String objective) {
        assert objective != null : "Task objective should be non-null";
        this.objective = objective;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a <code>String</code> array encoding of this task,
     * to be used to create an exact copy of this task.
     *
     * @return the <code>String[]</code> encoding of this task.
     */
    public abstract String[] save();

    /**
     * Returns <code>true</code> if this task occurs on
     * or before <code>date</code>.
     *
     * @param date date to compare against.
     * @return true if this task occurs on or before <code>date</code>.
     */
    public abstract boolean isBeforeDate(LocalDateTime date);

    /**
     * Returns <code>true</code> if this task occurs on
     * or after <code>date</code>.
     *
     * @param date date to compare against.
     * @return true if this task occurs on or after <code>date</code>.
     */
    public abstract boolean isAfterDate(LocalDateTime date);

    /**
     * Returns <code>true</code> if this task's objective
     * matches the specified regular expression.
     *
     * @param regex regular expression to match against.
     * @return true if this task's objective matches <code>regex</code>.
     */
    public boolean hasMatchingObjective(String regex) {
        return objective.matches(regex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + objective;
    }
}

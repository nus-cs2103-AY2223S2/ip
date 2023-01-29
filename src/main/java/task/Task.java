package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a textual objective, completion status,
 * and some chronological order.
 */
public abstract class Task {
    protected String objective;
    protected boolean done;

    /** <code>DateTimeFormatter</code> format for parsing supplied time <code>String</code>s. */
    public static final String DATE_IN_FMT_STR = "yyyy-MM-dd HH:mm";
    /** <code>DateTimeFormatter</code> format for outputting time <code>String</code>s. */
    public static final String DATE_OUT_FMT_STR = "dd LLL yyyy hh:mm a";
    /** <code>DateTimeFormatter</code> for parsing supplied time <code>String</code>s. */
    public static final DateTimeFormatter DATE_IN_FMT = DateTimeFormatter.ofPattern(DATE_IN_FMT_STR);
    /** <code>DateTimeFormatter</code> for outputting time <code>String</code>s. */
    protected static final DateTimeFormatter DATE_OUT_FMT = DateTimeFormatter.ofPattern(DATE_OUT_FMT_STR);

    /**
     * Creates a task with the specified textual objective.
     *
     * @param objective the description of this task's objective
     */
    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        done = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        done = false;
    }

    /**
     * Returns a <code>String</code> array encoding of this task,
     * to be used to create an exact copy of this task.
     *
     * @return the <code>String[]</code> encoding of this task
     */
    public abstract String[] save();

    /**
     * Returns <code>true</code> if this task occurs on
     * or before <code>date</code>.
     *
     * @param date the date to compare against
     * @return true if this task occurs on or before <code>date</code>
     */
    public abstract boolean beforeDate(LocalDateTime date);

    /**
     * Returns <code>true</code> if this task occurs on
     * or after <code>date</code>.
     *
     * @param date the date to compare against
     * @return true if this task occurs on or after <code>date</code>
     */
    public abstract boolean afterDate(LocalDateTime date);

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + objective;
    }
}

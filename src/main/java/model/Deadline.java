package model;

import utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String TAG = "[D]";

    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);

        try {
            LocalDateTime dateTime = DateTimeParser.parse(deadline);
            this.deadline = DateTimeParser.format(dateTime);
        } catch (DateTimeParseException e) {
            this.deadline = deadline;
        }
    }

    /**
     * Returns the <code>Task</code> type.
     *
     * @return The <code>Task</code> type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns the deadline attached to the <code>Deadline</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no start dateTime attached
     * to any <code>Deadline</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getStartDateTime() {
        return Task.EMPTY;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no end dateTime attached
     * to any <code>Deadline</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getEndDateTime() {
        return Task.EMPTY;
    }

    /**
     * Returns a <code>String</code> representation of the <code>Deadline</code> task.
     *
     * @return A <code>String</code> representation of the <code>Deadline</code> task.
     */
    @Override
    public String toString() {
        return Deadline.TAG + super.toString() + " (by: " + this.deadline + ")";
    }
}

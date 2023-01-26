package task;

import helper.DateTimeHelper;

import exception.InvalidDateFormatException;

import java.time.LocalDateTime;

/**
 * Represents a deadline task that should be done by a given time.
 */
public class Deadline extends Task{
    private LocalDateTime deadline;

    Deadline(String content, String deadlineString) throws InvalidDateFormatException {
        super(content);
        this.deadline = DateTimeHelper.parse(deadlineString);
    }

    /**
     * Checks if the deadline is the given datetime object.
     *
     * @param dt The given datetime object.
     * @return Whether the deadline is the given datetime object.
     */
    public boolean occursOn(LocalDateTime dt) {
        return dt.equals(this.deadline);
    }

    Deadline(String content, boolean done, String deadlineString) throws InvalidDateFormatException {
        super(content, done);
        this.deadline = DateTimeHelper.parseFormattedDateTime(deadlineString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeHelper.stringify(this.deadline) + ")";
    }
}

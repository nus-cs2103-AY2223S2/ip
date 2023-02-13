package saturday.models;

import java.time.temporal.TemporalAccessor;

import saturday.utilities.DateTimeParser;
/**
 * The Deadline class is a model class that represents a deadline. It extends the Task class and
 * provides additional functionality for handling the deadline time of the task.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class Deadline extends Task {
    private TemporalAccessor deadline;

    /**
     * Constructs a new deadline with the specified description, and deadline time.
     *
     * @param description the description of the deadline
     * @param deadline the deadline time of the task
     */
    public Deadline(int index, String description, String deadline) {
        super(index, description);
        this.deadline = DateTimeParser.parseDate(deadline);
    }

    /**
     * Returns the deadline time of the task.
     *
     * @return the deadline time of the task
     */
    public TemporalAccessor getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of the task. The format of the returned string is "[D]"
     * followed by the result of the toString() method from the superclass,
     * and the deadline time of the task in the format "(by: deadline_time)".
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return DateTimeParser.printDateTime(deadline);
    }
}

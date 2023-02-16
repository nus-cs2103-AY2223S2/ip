package boo.task;

import java.time.temporal.Temporal;

import boo.datetime.DateTime;

/**
 * Represents a deadline {@code Task} that can be kept track of, having a deadline associated with the task.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private Temporal deadline;

    /** A string containing the raw deadline string **/
    private String rawDeadline;

    /** A string where the deadline is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedDeadline;

    /**
     * Constructs a {@code Deadline} task.
     *
     * @param taskName The name of the task.
     * @param rawDeadline The raw deadline string input by user.
     * @param deadline The deadline {@code Temporal} object for this given deadline task.
     */
    public Deadline(String taskName, String rawDeadline, Temporal deadline) {
        super(taskName);
        this.rawDeadline = rawDeadline;
        this.deadline = deadline;
        this.formattedDeadline = DateTime.formatDate(deadline);
    }

    /**
     * Gets the status of the deadline task.
     *
     * @return a {@code String} indicating the type, status and deadline for the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "D";
        return (this.isDone)
               ? "[" + typeOfTask + "][X] " + this.taskName + (" (by: ") + this.formattedDeadline + ")"
               : "[" + typeOfTask + "][ ] " + this.taskName + (" (by: ") + this.formattedDeadline + ")";
    }


    /**
     * Gets the deadline object of the task.
     *
     * @return the {@code Temporal} encapsulating the date and time of the deadline.
     */
    public Temporal getDeadline() {
        return deadline;
    }

    /**
     * Gets the raw deadline of the task.
     *
     * @return the {@code String} containing the raw date and time of the deadline.
     */
    public String getRawDeadline() {
        return rawDeadline;
    }
}

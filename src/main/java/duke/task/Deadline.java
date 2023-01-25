package duke.task;

import duke.datetime.DateTime;

import java.time.temporal.Temporal;
/**
 * Represents a deadline <code>Task</code> that can be kept track of, having a deadline associated with the task.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private Temporal deadline;

    /** A string containing the raw deadline string **/
    private String rawDeadline;

    /** A string where the deadline is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedDeadline;

    /**
     * Constructs a <code>Deadline</code> task.
     *
     * @param taskName The name of the task.
     * @param rawDeadline The raw deadline string input by user.
     * @param deadline The deadline <code>Temporal</code> object for this given deadline task.
     */
    public Deadline(String taskName, String rawDeadline, Temporal deadline) {
        super(taskName);
        this.rawDeadline = rawDeadline;
        this.deadline= deadline;
        this.formattedDeadline = DateTime.formatDate(deadline);
    }
    
    /**
     * Gets the status of the deadline task.
     *
     * @return a <code>String</code> indicating the type, status and deadline for the task.
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
     * @return the <code>Temporal</code> encapsulating the date and time of the deadline.
     */
    public Temporal getDeadline() {
        return deadline;
    }

    /**
     * Gets the raw deadline of the task.
     *
     * @return the <code>String</code> containing the raw date and time of the deadline.
     */
    public String getRawDeadline() { return rawDeadline;}
}

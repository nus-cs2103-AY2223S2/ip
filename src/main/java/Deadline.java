import java.time.temporal.Temporal;
/**
 * Represents a deadline Task that can be kept track of, having a deadline associated with the task.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private Temporal deadline;

    /** A string containing the raw deadline string **/
    private String rawDeadline;

    /** A string where the deadline is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedDeadline;

    /**
     * Constructs a Deadline task.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline for this given deadline task.
     */
    public Deadline(String taskName, String rawDeadline, Temporal deadline) {
        super(taskName);
        this.rawDeadline = rawDeadline;
        this.deadline= deadline;
        this.formattedDeadline = Task.formatDate(deadline);
    }
    
    /**
     * Gets the status of the deadline task.
     *
     * @return a String indicating the type, status and deadline for the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "D";
        return (this.isDone)
               ? "[" + typeOfTask + "][X] " + this.taskName + (" (by: ") + this.formattedDeadline + ")"
               : "[" + typeOfTask + "][ ] " + this.taskName + (" (by: ") + this.formattedDeadline + ")";
    }


    /*
     * Gets the date object of the task.
     *
     * @return the Temporal encapsulating the date and time.
     */
    public Temporal getDeadline() {
        return deadline;
    }

    /**
     * Gets the raw date of the task.
     *
     * @return the String containing the raw date and time.
     */
    public String getRawDeadline() { return rawDeadline;}
}

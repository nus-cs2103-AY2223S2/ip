import java.time.temporal.Temporal;
/**
 * Represents an Event Task that can be kept track of, starting and ending at a specific date/time.
 */
public class Event extends Task {

    /** Start date of the event. */
    private Temporal startDate;

    /** End date of the event. */
    private Temporal endDate;


    /** A string containing the raw start date string **/
    private String rawStartDate;

    /** A string containing the raw end date string **/
    private String rawEndDate;



    /** A string where the start date is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedStartDate;
    /** End date of the event. */

    /** A string where the deadline is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedEndDate;

    /**
     * Constructs an Event task.
     *
     * @param taskName The name of the task.
     * @param startDate The start date of the event task.
     * @param endDate The end date of the event task.
     */
    public Event(String taskName, String rawStartDate, String rawEndDate, Temporal startDate, Temporal endDate) {
        super(taskName);
        this.rawStartDate = rawStartDate;
        this.rawEndDate = rawEndDate;
        this.startDate = startDate;
        this.endDate = endDate;
        formattedStartDate = Task.formatDate(startDate);
        formattedEndDate = Task.formatDate(endDate);
    }

    /**
     * Gets the status of the event task.
     *
     * @return a String indicating the type, status and time period for the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "E";
        return (this.isDone)
                ? "[" + typeOfTask + "][X] " + this.taskName + (" (from: ") + this.formattedStartDate +
                        " to: " + this.formattedEndDate + ")"
                : "[" + typeOfTask + "][ ] " + this.taskName + (" (from: ") + this.formattedStartDate +
                        " to: " + this.formattedEndDate + ")";
    }


    /*
     * Gets the start date object of the task.
     *
     * @return the Temporal encapsulating the date and time of the start date.
     */
    public Temporal getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date object of the task.
     *
     * @return the Temporal encapsulating the date and time of the end date.
     */
    public Temporal getEndDate() {
        return endDate;
    }

    /**
     * Gets the raw start date of the task.
     *
     * @return the String containing the raw start date and time.
     */
    public String getRawStartDate() { return rawStartDate;}

    /**
     * Gets the raw end date of the task.
     *
     * @return the String containing the raw end date and time.
     */
    public String getRawEndDate() { return rawEndDate;}
}

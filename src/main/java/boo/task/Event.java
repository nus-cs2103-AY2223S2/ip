package boo.task;

import java.time.temporal.Temporal;

import boo.datetime.DateTime;

/**
 * Represents an event {@code Task} that can be kept track of, starting and ending at a specific date/time.
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

    /** A string where the deadline is formatted in either yyyy-MM-dd HH:mm or yyyy-MM-dd. */
    private String formattedEndDate;

    /**
     * Constructs an {@code Event} task.
     *
     * @param taskName The name of the task.
     * @param rawStartDate The raw start date from the user.
     * @param rawEndDate The raw end date from the user.
     * @param startDate The start date {@code Temporal} object for this event.
     * @param endDate The end date {@code Temporal} object for this event.
     */
    public Event(String taskName, String rawStartDate, String rawEndDate, Temporal startDate, Temporal endDate) {
        super(taskName);
        this.rawStartDate = rawStartDate;
        this.rawEndDate = rawEndDate;
        this.startDate = startDate;
        this.endDate = endDate;
        formattedStartDate = DateTime.formatDate(startDate);
        formattedEndDate = DateTime.formatDate(endDate);
    }

    /**
     * Gets the status of the event task.
     *
     * @return a {@code String} indicating the type, status and time period for the task.
     */
    @Override
    public String getStatusOfTaskInString() {
        String typeOfTask = "E";
        return (this.isDone)
                ? "[" + typeOfTask + "][X] " + this.taskName + (" (from: ") + this.formattedStartDate
                        + " to: " + this.formattedEndDate + ")"
                : "[" + typeOfTask + "][ ] " + this.taskName + (" (from: ") + this.formattedStartDate
                        + " to: " + this.formattedEndDate + ")";
    }


    /**
     * Gets the start date object of the task.
     *
     * @return the {@code Temporal} encapsulating the date and time of the start date.
     */
    public Temporal getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date object of the task.
     *
     * @return the {@code Temporal} encapsulating the date and time of the end date.
     */
    public Temporal getEndDate() {
        return endDate;
    }

    /**
     * Gets the raw start date of the task.
     *
     * @return the {@code String} containing the raw start date and time.
     */
    public String getRawStartDate() {
        return rawStartDate;
    }

    /**
     * Gets the raw end date of the task.
     *
     * @return the {@code String} containing the raw end date and time.
     */
    public String getRawEndDate() {
        return rawEndDate;
    }
}

/**
 * Represents an Event Task that can be kept track of, starting and ending at a specific date/time.
 */
public class Event extends Task {
    /** Start date of the event. */
    private String startDate;
    /** End date of the event. */
    private String endDate;

    /**
     * Constructs an Event task.
     *
     * @param taskName The name of the task.
     * @param startDate The start date of the event task.
     * @param endDate The end date of the event task.
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
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
                ? "[" + typeOfTask + "][X] " + this.taskName + (" (from: ") + this.startDate +
                        " to: " + this.endDate + ")"
                : "[" + typeOfTask + "][ ] " + this.taskName + (" (from: ") + this.startDate +
                        " to: " + this.endDate + ")";
    }

    /**
     * Gets the start date of the event task.
     *
     * @return the start date of the event task.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the event task.
     *
     * @return the end date of the event task.
     */
    public String getEndDate() {
        return this.endDate;
    }
}

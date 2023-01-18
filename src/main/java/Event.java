/**
 * This class represents an Event Task that can be kept track of,
 * starting and ending at a specific date/time.
 *
 * @version CS2103T AY22/23 Sem 2 Individual Project
 * @author A0233828Y Eugene Tang
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * Constructs an Event task.
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
}

package fideline.task;

/**
 * Representation of a task that has both a start and end timing.
 *
 * @author Fun Leon
 */
public class Event extends Task {

    /** String representation of the task's start time */
    private String startTime;

    /** String representation of the task's end time */
    private String endTime;

    /**
     * Constructs a task object with bith a start and end timing.
     *
     * @param description Title given to the task.
     * @param startTime Time when the task is supposed to start.
     * @param endTime Time when the task is supposed to end.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String stringFormat = "[E]%s (from: %s to: %s)";
        return String.format(stringFormat, super.toString(), startTime, endTime);
    }

    @Override
    public String getStorageString() {
        String stringFormat = "E|%s|%s|%s";
        return String.format(stringFormat, super.getStorageString(),
                startTime, endTime);
    }

    @Override
    public boolean equals(Task task) {
        boolean isEvent = task instanceof Event;
        if (!isEvent) {
            return false;
        }
        boolean isSameStart = startTime.equals(((Event) task).startTime);
        boolean isSameEnd = endTime.equals(((Event) task).endTime);
        return isSameStart && isSameEnd && super.equals(task);
    }
}

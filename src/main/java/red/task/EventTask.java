package red.task;

/**
 * This class is for tasks that are considered an event with a timeframe.
 */
public class EventTask extends Task {

    protected String start;
    protected String end;

    /**
     * Constructor for a EventTask that takes in a description as well as starting and ending time.
     *
     * @param description The description of the EventTask.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + start + " To: " + end + ")";
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask checkedObj = (EventTask) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);
        boolean isSameStart = this.start.equals(checkedObj.start);
        boolean isSameEnd = this.end.equals(checkedObj.end);

        if (isSameDescription && isSameStart && isSameEnd) {
            return true;
        }

        return false;
    }


}
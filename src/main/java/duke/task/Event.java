package duke.task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "[E]" + getStatusIcon() + " " + description
                + " (from: "
                + startTime + " to: "
                + endTime + ")";
    }
}
package duke.model;

public class Event extends Task{
    private String startTime;
    private String endTime;
    protected Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

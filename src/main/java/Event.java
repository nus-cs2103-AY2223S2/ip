public class Event extends Task{
    private final String taskType = "[E]";
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}

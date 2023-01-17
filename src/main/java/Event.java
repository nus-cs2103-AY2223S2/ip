public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String eventString = "(from: " + startTime + " to: " + endTime + ")";
        return "[E]" + super.toString() + eventString;
    }
}

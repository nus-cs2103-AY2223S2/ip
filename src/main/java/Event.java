public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String taskName, Boolean isDone, String startTime, String endTime) {
        super(taskName, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String dataFormat() {
        return "T|" + super.dataFormat() + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

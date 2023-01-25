public class Event extends Task {

    private final String fromTime;
    private final String toTime;

    public Event(String taskDescription, String fromTime, String toTime) {
        this.taskDescription = taskDescription;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s from: %s to: %s", super.toString(), fromTime, toTime);
    }
}

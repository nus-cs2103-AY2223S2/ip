public class Event extends Task {

    private String fromTime;
    private String toTime;

    public Event(String task, String type, String from, String to) {
        super(task, type);
        fromTime = from;
        toTime = to;
    }

    @Override
    public String toString() {
        return String.format("%s from: %s to: %s", super.toString(), fromTime, toTime);
    }
}

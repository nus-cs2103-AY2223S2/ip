//Event is one of 3 types of Task, so it must be a child of Task
public class Event extends Task {
    protected String timeRange;

    public Event(String name, String timeRange) {
        super(name);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + timeRange + ")";
    }
}

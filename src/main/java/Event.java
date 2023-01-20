//Event is one of 3 types of Task, so it must be a child of Task
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(from: " + startTime + " to: " + endTime + ")";
    }
}

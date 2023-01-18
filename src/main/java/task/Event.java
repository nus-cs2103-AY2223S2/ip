package task;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String start, String end, String desc) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}

package duke;

public class Event extends Task {
    String icon = "[E]";
    String from;
    String to;

    public Event(String details, String from, String to) {
        super(details);
        this.from = from.replace("from", "");
        this.to = to.replace("to", "");
    }

    public Event(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return icon + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}

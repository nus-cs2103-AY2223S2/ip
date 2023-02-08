package duke.tasks;

public class Event extends Task {

    public final String start;
    public final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}


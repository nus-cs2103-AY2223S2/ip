package Tasks;
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + this.start + " to: " + this.end + ")";
    }

    public String toFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}

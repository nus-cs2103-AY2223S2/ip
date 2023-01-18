public class Event extends Task {
    private String type;
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.type = "E";
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}

public class Events extends Task {
    protected String start;
    protected String end;
    public Events(String description, String start, String end) {
        super(description);
        this.start = start.substring(5);
        this.end = end.substring(3);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}

public class Event extends Task {
    private final String start;
    private final String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")");
        return sb.toString();
    }
}

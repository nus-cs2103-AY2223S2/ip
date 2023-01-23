public class Event extends Task {
    String start, end;
    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[E] " + task + " (from: " + start + " to: " + end + ")";
    }
}

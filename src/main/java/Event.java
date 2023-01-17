public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        Task.numTask++;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + ", " + "to: " + this.end + ")";
    }
}
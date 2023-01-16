public class Event extends Task {
    private String start;
    private String end;

    public Event(String instructions, String start, String end) {
        super(instructions);
        this.start = start.substring(5);
        this.end = end.substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
public class Event extends Task {
    private String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + ")";
    }
}
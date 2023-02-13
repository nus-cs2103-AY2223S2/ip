public class Event extends Task {
    private String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    @Override
    public String getOutputFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, from);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + ")";
    }
}
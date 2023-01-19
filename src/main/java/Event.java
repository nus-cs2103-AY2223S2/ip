public class Event extends Task {
    private String desc;
    private String from;
    private String to;

    public Event(int id, String description, String from, String to) {
        super(id);
        desc = description;
        this.from = from;
        this.to = to;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to %s)", description(), from, to);
    }
}

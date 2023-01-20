public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getFileRepresentation() {
        return "E" + "@" + (super.isDone() ? "1" : "0") + "@" + this.getName() + "@" + this.from + "@" + this.to;
    }
}

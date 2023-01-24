public class EventTask extends Task {

    private final String from;
    private final String to;

    EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toDukeFileString() {
        return "E|" + super.toDukeFileString() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

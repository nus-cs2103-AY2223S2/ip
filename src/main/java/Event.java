public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description.trim());
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toBackup() {
        return "D | " + super.toBackup() + " | " + from + " | " + to + "\n";
    }
}
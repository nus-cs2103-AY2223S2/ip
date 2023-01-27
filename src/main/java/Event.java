public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.type ="E";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s | %s | %s", this.type, this.isDone, this.taskName, this.from, this.to);
    }
}

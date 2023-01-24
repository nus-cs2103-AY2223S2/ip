public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        if (super.getStatus()) {
            return String.format("[E][X] %s (from: %s to: %s)\n", super.getTaskName(), this.from, this.to);
        } else {
            return String.format("[E][ ] %s (from: %s to: %s)\n", super.getTaskName(), this.from, this.to);
        }
    }
}

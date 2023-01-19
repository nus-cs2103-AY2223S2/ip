public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private String getTaskClass() {
        return "T";
    }

    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskClass(), this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}

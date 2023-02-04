public class Event extends Task {
    private String[] period;

    public Event(String description, String[] period) {
        super(description);
        this.period = period;
    }

    public String toData() {
        return String.format("E | %s | %s | %s to %s",this.getStatusIcon(), this.getDescription(), this.period[0], this.period[1]);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from: " + this.period[0] + " to: " + this.period[1] + ")";
    }
}

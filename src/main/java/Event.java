public class Event extends Task {
    private String[] period;

    public Event(String description, String[] period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from: " + this.period[0] + " to: " + this.period[1] + ")";
    }
}

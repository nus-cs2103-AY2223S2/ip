public class TaskEvent extends Task {
    public final String fromTime;
    public final String toTime;

    public TaskEvent(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)", 
            super.toString(), 
            this.fromTime, 
            this.toTime
        );
    }
}

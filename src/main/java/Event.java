public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    public Event(String description, boolean isCompleted, String start, String end) {
        super(description, isCompleted);
        this.start = start;
        this.end = end;
    }
    public String getStartTime() {
        return this.start;
    }
    public String getEndTime() {
        return this.end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
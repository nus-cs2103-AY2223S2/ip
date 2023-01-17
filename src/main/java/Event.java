public class Event extends Task {
    private final String start;
    private final String end;

    private Event(String desc, boolean done, String start, String end) {
        super(desc, done);
        this.start = start;
        this.end = end;
    }

    public Event(String desc, String start, String end) {
        this(desc, false, start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public Event markDone() {
        return new Event(this.desc, true, this.start, this.end);
    }

    @Override
    public Event markNotDone() {
        return new Event(this.desc, false, this.start, this.end);
    }
    
}

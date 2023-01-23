public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event target = (Event) o;
            return target.description.equals(this.description)
                    && target.start.equals(this.start)
                    && target.end.equals(this.end);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}

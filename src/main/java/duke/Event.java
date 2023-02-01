package duke;

public class Event extends Task {
    protected Date from;
    protected Date to;

    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFileRepresentation() {
        return "event|" + super.getFileRepresentation()
                + "|" + this.from.asFileDate()
                + "|" + this.to.asFileDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from
                + " to: " + this.to + ")";
    }
}

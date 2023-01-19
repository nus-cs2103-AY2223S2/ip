public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        start = start.replaceAll("from", "");
        end = end.replaceAll("to", "");
        this.start = start;
        this.end = end;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.start + " to:" + this.end + ")";
    }
}

public class Event extends Tasks{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to+")";
    }
}

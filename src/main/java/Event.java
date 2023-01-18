public class Event extends Task {
    protected String from;
    protected String to;

    Event(String title, String from, String to) throws DukeException {
        super(title);
        this.from = from.replace("/from", "").trim();
        this.to = to.replace("/to", "").trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
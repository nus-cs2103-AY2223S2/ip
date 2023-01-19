public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String event, String from, String to) {
        super(event);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(from:%sto:%s)", super.toString(), from, to);
    }
}

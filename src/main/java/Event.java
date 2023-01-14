public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s) (to: %s)", super.toString(), from, to);
    }
}

public class Event extends Task {
    private String from;
    private String to;

    public Event(String input, String from, String to) {
        super(input);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}

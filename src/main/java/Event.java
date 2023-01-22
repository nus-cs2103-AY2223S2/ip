public class Event extends Task {
    private String from;
    private String to;
    public Event(String input, String from, String to) {
        super(input);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "   [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}

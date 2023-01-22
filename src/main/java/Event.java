public class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    Event(String name, String from, String to, String status) {
        super(name, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from +
                " to: " + to + ")";
    }

    @Override
    public String asTokens() {
        return "E," + super.asTokens() +
                ',' + this.from +
                ',' + this.to;
    }
}

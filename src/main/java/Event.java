public class Event extends Task {
    private static String from;
    private static String to;
    Event(String description, String from, String to) {
        super(description);
        this.symbol = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }
}

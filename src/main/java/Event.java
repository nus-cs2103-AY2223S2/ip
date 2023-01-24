public class Event extends Task {
    private final String from;
    private final String to;

    Event(String description, String from, String to)  {
        super(description);
        this.from = from;
        this.to = to;
        this.symbol = "E";
    }

    @Override
    public String asDataFormat() {
        return super.asDataFormat("from:" + this.from, "to:" + this.to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }

}

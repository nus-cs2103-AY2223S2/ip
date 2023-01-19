public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return description + " (from: " + from + " to: " + to + ")";
    }

}

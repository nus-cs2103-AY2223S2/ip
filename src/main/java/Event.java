public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("%s | %s to %s", this.description, this.start, this.end);
    }
}

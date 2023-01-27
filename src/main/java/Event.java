public class Event extends Task {
    String start;
    String end;

    Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("E | %s | from: %s | to: %s", super.toString(), start, end);
    }
}

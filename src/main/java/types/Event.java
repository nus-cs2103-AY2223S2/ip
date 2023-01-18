package types;

public class Event extends Task {
    private final String start;
    private final String end;

    private Event(String n, String d1, String d2) {
        super(n, "E");
        start = d1;
        end = d2;
    }

    public static Event create(String n, String d1, String d2) {
        return new Event(n, d1, d2);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s, to: %s)", super.toString(), start, end);
    }
}

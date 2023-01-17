public class Events extends Task {
    private String start;
    private String end;

    public Events (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "  + this.start
                + " to: " + this.end + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + this.start
                + " | " + this.end + "\n";
    }
}

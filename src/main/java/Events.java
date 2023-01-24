public class Events extends Task {
    protected String start;
    protected String end;
    public Events(String description, String start, String end) {
        this(false, description, start, end);
    }

    public Events(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start, this.end);
    }

    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "E" + divider + isMarked + divider + this.description + divider +
                this.start + divider + this.end;
    }
}

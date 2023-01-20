package dukes.engine;

class Event extends Task {
    String start;
    String end;

    Event(String taskName, String start, String end) {
        super(taskName);
        this.tag = "event";
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.start + " to: " + this.end + ")";
    }
}

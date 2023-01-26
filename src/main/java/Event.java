public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        super.type = 'E';
    }

    @Override
    public String taskInFileFormat() {
        return super.taskInFileFormat() + " | " + start + "-" + end;
    }

    @Override
    public String toString() {
        return "[" + super.type + "]" + super.toString() + " (from: " + start + " to: "
                + end + ")";
    }
}
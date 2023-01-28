public class Event extends Task {
    private String start;
    private String end;
    private String task;

    public Event(String task, String start, String end) {
        super(task);
        this.task = task;
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}

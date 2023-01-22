package duke.task;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    public Event(String name, String start, String end) {
        this(name, false, start, end);
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String serialize() {
        String serialized = String.format("%s|%s|%s", super.serialize(), this.start, this.end);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("%s (from: %s to: %s)", super.toString(), this.start, this.end);
        return s;
    }
}

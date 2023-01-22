package duke.task;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        String s = String.format("%s (from: %s to: %s)", super.toString(), this.start, this.end);
        return s;
    }
}

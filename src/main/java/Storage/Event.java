package Storage;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + status() + getTask() + duration();
    }

    private String duration() {
        return " (" + this.from + " - " + this.to + ")";
    }
}

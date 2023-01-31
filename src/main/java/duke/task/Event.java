package duke.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, String from, String to, boolean isDone) {
        super(taskName);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String[] encode() {
        String[] res = new String[]{"E", this.getStatusIcon(), this.taskName, this.from, this.to};
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
    }
}
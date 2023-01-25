package Event;

import Task.Task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getCommand() {
        return this.isDone 
        ? "1 event " + this.description + " /from " + this.from + " /to " + this.to
        : "0 event " + this.description + " /from " + this.from + " /to " + this.to;

    }
}

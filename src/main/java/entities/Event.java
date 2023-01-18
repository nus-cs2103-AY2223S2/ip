package entities;

import enums.TaskType;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String ...flags) {
        super(description);
        this.from = flags[0];
        this.to = flags[1];
    }

    public SerializableTask serialize() {
        String flags = from + "-" + to;
        return new SerializableTask(TaskType.EVENT, isDone, description, flags);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " +  to + ")";
    }
}
